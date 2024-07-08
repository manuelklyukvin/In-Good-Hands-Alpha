package com.klyukvin.ingoodhands.ui.screen.authentication.login.mapper

import com.klyukvin.domain.usecase.authentication.login.model.DomainLoginFields
import com.klyukvin.domain.usecase.database.user.model.DomainLoginUserData
import com.klyukvin.ingoodhands.ui.screen.authentication.login.model.UiLoginFields

class LoginFieldsMapper {

    fun mapToDomain(fields: UiLoginFields): DomainLoginFields {
        with(fields) {
            return DomainLoginFields(
                email = email,
                password = password
            )
        }
    }

    fun mapToDomainUserData(fields: UiLoginFields): DomainLoginUserData {
        with(fields) {
            return DomainLoginUserData(
                email = email,
                password = password
            )
        }
    }
}