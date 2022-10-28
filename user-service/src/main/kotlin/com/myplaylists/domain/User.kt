package com.myplaylists.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

class User(
    @Id @Column("user_id")
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