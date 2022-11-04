package com.myplaylists.dto


data class UserDTO(
    val email: String,
    val name: String,
    val nickname: String,
    val oauthType: OauthType,
): SuccessResponse() {

}