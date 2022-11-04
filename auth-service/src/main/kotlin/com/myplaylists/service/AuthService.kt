package com.myplaylists.service

import com.myplaylists.dao.UserRepository
import com.myplaylists.domain.User
import com.myplaylists.dto.OauthDTO
import com.myplaylists.dto.UserDTO
import com.myplaylists.exception.SignupRequiredException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class AuthService(
    @Qualifier("userServiceClient")
    private val userServiceClient: WebClient,
    private val userRepository: UserRepository, // TODO: 추후에 회원 서비스와는 독립적인 DB 사용하도록 properties 수정해주자.
) {

    /**
     * 내플리스 로그인 처리
     * 1. 존재하지 않는 이메일이면 회원가입 요구
     * 2. 존재하는 이메일이면 OAUTH 타입에 따라 처리
     *   2-1. 같은 OAUTH 타입이 이미 존재하면 최신 정보로 업데이트 후 로그인 처리
     *   2-2. 새로운 OAUTH 타입이라면 회원가입 요구
     */
    fun login(oauthDTO: OauthDTO): Mono<UserDTO> {
        return userRepository.findAllByEmail(oauthDTO.email)
            .switchIfEmpty {
                throw SignupRequiredException()
            }.filter { user ->
                user.oauthType == oauthDTO.oauthType
            }.flatMap { user -> updateUserInfo(user, oauthDTO)
            }.next()
            .switchIfEmpty {
                Mono.error(SignupRequiredException())
            }
    }

    // 유저서비스의 `유저 정보 업데이트` API 호출
    // TODO: 이벤트 기반으로 결합도 낮춰보자
    // 예를 들어 인증 서비스에서 이벤트 발행하면 (eventPublisher.login())
    // 유저 서비스에서 이벤트 구독하여 유저 정보 갱신.
    private fun updateUserInfo(user: User, oauthDTO: OauthDTO): Mono<UserDTO> {
        val userDTO = UserDTO(
            email = user.email,
            name = oauthDTO.name,
            nickname = user.nickname,
            oauthType = user.oauthType
        )
        userServiceClient
            .put()
            .uri("/users/${user.id}")
            .body(BodyInserters.fromValue(userDTO))
            .retrieve()
            .bodyToMono(UserDTO::class.java)

        // 유저 서비스 호출 결과와 관련없이 리턴
        return Mono.just(userDTO)
    }

}