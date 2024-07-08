package com.klyukvin.ingoodhands.ui.screen.authentication.login.mapper

import com.klyukvin.domain.usecase.authentication.login.model.DomainLoginErrors
import com.klyukvin.ingoodhands.ui.screen.authentication.login.model.UiLoginErrors

class LoginErrorsMapper {

    fun mapToUi(errors: DomainLoginErrors): UiLoginErrors {
        with(errors) {
            return UiLoginErrors(
                emailError = emailError,
                passwordError = passwordError
            )
        }
    }

    fun mapToDomain(errors: UiLoginErrors): DomainLoginErrors {
        with(errors) {
            return DomainLoginErrors(
                emailError = emailError,
                passwordError = passwordError
            )
        }
    }
}