package com.myplaylists.dto

import com.myplaylists.domain.User

data class UserDTO(
    val email: String,
    val name: String,
    val nickname: String,
): SuccessResponse() {
    companion object {
        fun of(user: User): UserDTO =
            UserDTO(
                email = user.email,
                name = user.name,
                nickname = user.nickname
            )
    }
}