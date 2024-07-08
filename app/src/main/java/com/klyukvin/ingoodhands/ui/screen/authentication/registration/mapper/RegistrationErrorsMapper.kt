package com.klyukvin.ingoodhands.ui.screen.authentication.registration.mapper

import com.klyukvin.domain.usecase.authentication.registration.model.DomainRegistrationErrors
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.model.UiRegistrationErrors

class RegistrationErrorsMapper {

    fun mapToUi(errors: DomainRegistrationErrors): UiRegistrationErrors {
        with(errors) {
            return UiRegistrationErrors(
                usernameError = usernameError,
                firstnameError = firstnameError,
                lastnameError = lastnameError,
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
        }
    }

    fun mapToDomain(errors: UiRegistrationErrors): DomainRegistrationErrors {
        with(errors) {
            return DomainRegistrationErrors(
                usernameError = usernameError,
                firstnameError = firstnameError,
                lastnameError = lastnameError,
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
        }
    }
}