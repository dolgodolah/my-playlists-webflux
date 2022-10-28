package com.myplaylists.controller

import com.myplaylists.domain.OauthType
import com.myplaylists.dto.OauthDTO
import com.myplaylists.dto.UserDTO
import com.myplaylists.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController(
    private val userService: UserService,
) {


    @GetMapping("/users/{userId}")
    fun findUserById(@PathVariable userId: Long): Mono<UserDTO> {
        return userService.findUserById(userId)
    }
}