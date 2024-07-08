package com.klyukvin.ingoodhands.ui.screen.authentication.registration.mapper

import com.klyukvin.domain.usecase.authentication.registration.model.DomainRegistrationFields
import com.klyukvin.domain.usecase.database.user.model.DomainRegistrationUserData
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.model.UiRegistrationFields

class RegistrationFieldsMapper {

    fun mapToDomain(fields: UiRegistrationFields): DomainRegistrationFields {
        with(fields) {
            return DomainRegistrationFields(
                username = username,
                firstname = firstname,
                lastname = lastname,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
        }
    }

    fun mapToDomainUserData(fields: UiRegistrationFields): DomainRegistrationUserData {
        with(fields) {
            return DomainRegistrationUserData(
                username = username,
                firstname = firstname,
                lastname = lastname,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
        }
    }
}