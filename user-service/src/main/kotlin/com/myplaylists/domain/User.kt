package com.myplaylists.domain

import com.myplaylists.dto.OauthDTO
import com.myplaylists.dto.OauthType
import com.myplaylists.dto.UserDTO
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

    companion object {
        fun of(oauthDTO: OauthDTO): User =
            User(
                email = oauthDTO.email,
                name = oauthDTO.name,
                nickname = oauthDTO.name,
                oauthType = oauthDTO.oauthType
            )

    }

    fun updateNickname(nickname: String) {
        if (this.nickname != nickname) {
            this.nickname = nickname
            this.updatedDate = LocalDateTime.now()
        }
    }

    fun toDTO(): UserDTO =
        UserDTO(
            email = this.email,
            name = this.name,
            nickname = this.nickname
        )
}