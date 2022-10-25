package com.myplaylists.service

import com.myplaylists.dto.UserDTO
import com.myplaylists.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun findUserById(userId: Long): Mono<UserDTO> {
        return userRepository.findById(userId).flatMap {
            Mono.just(UserDTO.of(it))
        }
    }
}