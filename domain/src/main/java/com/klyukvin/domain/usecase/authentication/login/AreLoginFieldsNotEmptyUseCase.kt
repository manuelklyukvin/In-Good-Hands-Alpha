package com.klyukvin.domain.usecase.authentication.login

import com.klyukvin.domain.usecase.authentication.login.model.DomainLoginFields

class AreLoginFieldsNotEmptyUseCase {

    operator fun invoke(fields: DomainLoginFields): Boolean {
        return with(fields) {
            email.trim().isNotEmpty() && password.trim().isNotEmpty()
        }
    }
}