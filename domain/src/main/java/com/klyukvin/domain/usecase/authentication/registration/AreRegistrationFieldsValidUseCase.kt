package com.klyukvin.domain.usecase.authentication.registration

import com.klyukvin.domain.usecase.authentication.registration.model.DomainRegistrationErrors

class AreRegistrationFieldsValidUseCase {
    
    operator fun invoke(errors: DomainRegistrationErrors): Boolean {
        return with(errors) {
            usernameError == null &&
            emailError == null &&
            passwordError == null &&
            confirmPasswordError == null
        }
    }
}