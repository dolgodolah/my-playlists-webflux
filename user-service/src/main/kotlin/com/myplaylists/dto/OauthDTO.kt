package com.myplaylists.dto

import com.myplaylists.domain.OauthType
import com.myplaylists.domain.User

class OauthDTO(
    val email: String,
    val name: String,
    val oauthType: OauthType
): SuccessResponse() {
    fun toEntity(): User =
        User(
            email = email,
            name = name,
            nickname = name,
            oauthType = oauthType
        )
}