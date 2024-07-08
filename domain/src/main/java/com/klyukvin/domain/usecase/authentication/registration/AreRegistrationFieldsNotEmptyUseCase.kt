package com.klyukvin.domain.usecase.authentication.registration

import com.klyukvin.domain.usecase.authentication.registration.model.DomainRegistrationFields

class AreRegistrationFieldsNotEmptyUseCase {

    operator fun invoke(fields: DomainRegistrationFields): Boolean {
        return with(fields) {
            username.trim().isNotEmpty() &&
            email.trim().isNotEmpty() &&
            password.trim().isNotEmpty() &&
            confirmPassword.trim().isNotEmpty()
        }
    }
}