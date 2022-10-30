package com.myplaylists.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

class User(
    @Id @Column("user_id")
    var id: Long? = null,
    var name: String,
    var nickname: String,
    var email: String,
    val oauthType: OauthType,
    var createdDate: LocalDateTime = LocalDateTime.now(),
    var updatedDate: LocalDateTime = LocalDateTime.now()
) {

    fun updateNickname(nickname: String) {
        if (this.nickname != nickname) {
            this.nickname = nickname
            this.updatedDate = LocalDateTime.now()
        }
    }
}

enum class OauthType(
    val value: Int,
) {
    NONE(0), KAKAO(1), GOOGLE(2)
}