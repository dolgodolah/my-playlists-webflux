package com.myplaylists.handler

import com.myplaylists.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Mono

@Component
class UserHandler(
    private val userService: UserService,
) {

    fun findUserById(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.pathVariable("userId").toLong()
        return userService.findUserById(userId).flatMap {
            ServerResponse.ok().json().bodyValue(it)
        }
    }
}