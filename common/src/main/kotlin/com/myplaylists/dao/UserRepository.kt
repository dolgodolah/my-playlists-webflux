package com.myplaylists.dao

import com.myplaylists.domain.User
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface UserRepository: R2dbcRepository<User, Long> {
    fun findAllByEmail(email: String): Flux<User>
}