package com.myplaylists.service

import com.myplaylists.dao.UserRepository
import com.myplaylists.domain.User
import com.myplaylists.dto.OauthDTO
import com.myplaylists.dto.UserDTO
import com.myplaylists.exception.BadRequestException
import com.myplaylists.exception.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun createUser(oauthDTO: OauthDTO): Mono<UserDTO> {
        if (!StringUtils.hasText(oauthDTO.email)) {
            throw BadRequestException("이메일은 필수입니다. 카카오로 로그인 시 이메일 제공에 동의하지 않았다면 카카오 계정 > 연결된 서비스 관리 > 내플리스 > 연결 끊기 후 이메일 제공에 동의해주세요.")
        }

        val user = User.of(oauthDTO)
        return userRepository.save(user).map { user ->
            user.toDTO()
        }
    }

    fun findUserById(userId: Long): Mono<UserDTO> {
        return userRepository.findById(userId).map { user ->
            user.toDTO()
        }.switchIfEmpty {
            Mono.error(NotFoundException("해당 사용자는 존재하지 않는 사용자입니다."))
        }
    }

    fun findAllUserByEmail(email: String): Flux<UserDTO> {
        return userRepository.findAllByEmail(email).map { user ->
            user.toDTO()
        }
    }

    fun updateUserInfo(userId: Long, userDTO: UserDTO): Mono<UserDTO> {
        if (!StringUtils.hasText(userDTO.nickname)) {
            return Mono.error(BadRequestException("닉네임이 공백이거나 입력되지 않았습니다."))
        }

        return userRepository.findById(userId).flatMap { user ->
            user.updateNickname(userDTO.nickname)
            userRepository.save(user)
        }.map { user ->
            user.toDTO()
        }.switchIfEmpty {
            Mono.error(NotFoundException("해당 사용자는 존재하지 않는 사용자입니다."))
        }
    }
}