package com.klyukvin.domain.usecase.authentication

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationFieldType

class ValidatePasswordUseCase(
    private val commonValidateFieldUseCase: CommonValidateFieldUseCase
) {

    operator fun invoke(password: String): Result<Unit, DomainAuthenticationErrorType> {
        return commonValidateFieldUseCase(password, DomainAuthenticationFieldType.PASSWORD)
    }
}