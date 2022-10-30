package com.myplaylists.service

import com.myplaylists.dto.OauthDTO
import com.myplaylists.dto.UserDTO
import com.myplaylists.exception.BadRequestException
import com.myplaylists.exception.NotFoundException
import com.myplaylists.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun createUser(oauthDTO: OauthDTO): Mono<UserDTO> {
        return userRepository.save(oauthDTO.toEntity()).map {
            UserDTO.of(it)
        }
    }

    fun findUserById(userId: Long): Mono<UserDTO> {
        return userRepository.findById(userId).map {
            UserDTO.of(it)
        }.switchIfEmpty {
            Mono.error(NotFoundException("해당 사용자는 존재하지 않는 사용자입니다."))
        }
    }

    fun findAllUserByEmail(email: String): Flux<UserDTO> {
        return userRepository.findAllByEmail(email).map { user ->
            UserDTO.of(user)
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
            UserDTO.of(user)
        }.switchIfEmpty {
            Mono.error(NotFoundException("해당 사용자는 존재하지 않는 사용자입니다."))
        }
    }
}