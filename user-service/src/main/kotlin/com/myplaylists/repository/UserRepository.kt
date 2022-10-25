package com.myplaylists.repository

import com.myplaylists.domain.OauthType
import com.myplaylists.domain.User
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class UserRepository(
) {
    fun findById(id: Long): Mono<User> {
        val user = User(1, "김규범", "돌고돌아", "dolgodolah@gmail.com", OauthType.GOOGLE)
        return Mono.justOrEmpty(user)
    }
}