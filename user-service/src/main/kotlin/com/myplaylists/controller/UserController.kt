package com.myplaylists.controller

import com.myplaylists.dto.OauthDTO
import com.myplaylists.dto.UserDTO
import com.myplaylists.service.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    fun signup(@RequestBody oauthDTO: OauthDTO): Mono<UserDTO> {
        return userService.createUser(oauthDTO)
    }

    @GetMapping("/users/{userId}")
    fun findUserById(@PathVariable userId: Long): Mono<UserDTO> {
        return userService.findUserById(userId)
    }

    @GetMapping("/users")
    fun findAllUserByEmail(@RequestParam email: String): Flux<UserDTO> {
        return userService.findAllUserByEmail(email)
    }

    @PutMapping("/users/{userId}")
    fun updateUserInfo(@PathVariable userId: Long, @RequestBody userDTO: UserDTO): Mono<UserDTO> {
        return userService.updateUserInfo(userId, userDTO)
    }
}