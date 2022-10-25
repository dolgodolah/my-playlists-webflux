package com.myplaylists.domain

class User(
    val id: Long? = null,
    var name: String,
    var nickname: String,
    var email: String,
    val oauthType: OauthType
) {
    constructor(name: String, nickname: String, email: String, oauthType: OauthType): this(null, name, nickname, email, oauthType)
}

enum class OauthType(
    val value: Int,
) {
    NONE(0), KAKAO(1), GOOGLE(2)
}