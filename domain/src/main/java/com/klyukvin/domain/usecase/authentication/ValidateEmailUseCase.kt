package com.klyukvin.domain.usecase.authentication

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationFieldType

class ValidateEmailUseCase(
    private val commonValidateFieldUseCase: CommonValidateFieldUseCase
) {

    operator fun invoke(email: String): Result<Unit, DomainAuthenticationErrorType> {
        val validationResult = commonValidateFieldUseCase(email, DomainAuthenticationFieldType.EMAIL)

        if (validationResult !is Result.Success) {
            return validationResult
        }
        if (!isEmailValid(email)) {
            return Result.Error(DomainAuthenticationErrorType.EMAIL_IS_INVALID)
        }
        return Result.Success(Unit)
    }

    private fun isEmailValid(email: String): Boolean {
        val regex = Regex("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-zA-Z]{2,}")
        return regex.matches(email)
    }
}