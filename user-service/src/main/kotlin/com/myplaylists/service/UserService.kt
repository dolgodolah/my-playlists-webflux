package com.myplaylists.service

import com.myplaylists.dto.UserDTO
import com.myplaylists.exception.NotFoundException
import com.myplaylists.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun findUserById(userId: Long): Mono<UserDTO> {
        return userRepository.findById(userId).flatMap {
            Mono.just(UserDTO.of(it))
        }.switchIfEmpty {
            Mono.error(NotFoundException("해당 사용자는 존재하지 않는 사용자입니다."))
        }
    }
}