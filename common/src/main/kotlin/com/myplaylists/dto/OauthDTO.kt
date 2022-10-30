package com.myplaylists.dto

class OauthDTO(
    val email: String,
    val name: String,
    val oauthType: OauthType
): SuccessResponse() {

}

enum class OauthType(
    val value: Int,
) {
    NONE(0), KAKAO(1), GOOGLE(2)
}