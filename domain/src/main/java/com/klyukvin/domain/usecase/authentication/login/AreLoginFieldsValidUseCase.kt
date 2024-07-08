package com.klyukvin.domain.usecase.authentication.login

import com.klyukvin.domain.usecase.authentication.login.model.DomainLoginErrors

class AreLoginFieldsValidUseCase {

    operator fun invoke(errors: DomainLoginErrors): Boolean {
        return with(errors) {
            emailError == null && passwordError == null
        }
    }
}