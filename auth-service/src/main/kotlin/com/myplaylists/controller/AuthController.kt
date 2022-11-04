package com.myplaylists.controller

import com.myplaylists.dto.OauthDTO
import com.myplaylists.dto.UserDTO
import com.myplaylists.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody oauthDTO: OauthDTO): Mono<UserDTO> {
        return authService.login(oauthDTO)
    }
}