package com.klyukvin.ingoodhands.common.mapper

import com.klyukvin.domain.common.model.DomainUser
import com.klyukvin.ingoodhands.common.model.UiUser

class UserMapper {

    fun mapToUi(user: DomainUser): UiUser {
        with(user) {
            return UiUser(
                id = id,
                username = username,
                firstname = firstname,
                lastname = lastname,
                email = email,
                password = password,
                isAdmin = isAdmin
            )
        }
    }
}