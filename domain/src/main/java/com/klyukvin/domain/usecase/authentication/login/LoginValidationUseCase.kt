package com.klyukvin.domain.usecase.authentication.login

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.GetAuthenticationErrorUseCase
import com.klyukvin.domain.usecase.authentication.ValidateEmailUseCase
import com.klyukvin.domain.usecase.authentication.ValidatePasswordUseCase
import com.klyukvin.domain.usecase.authentication.login.model.DomainLoginErrors
import com.klyukvin.domain.usecase.authentication.login.model.DomainLoginFields

class LoginValidationUseCase(
    private val getAuthenticationErrorUseCase: GetAuthenticationErrorUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) {

    operator fun invoke(fields: DomainLoginFields): DomainLoginErrors {
        with(fields) {
            val emailValidationResult = validateEmailUseCase(email)
            val passwordValidationResult = validatePasswordUseCase(password)

            val emailError = if (emailValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(emailValidationResult.error)
            } else {
                null
            }

            val passwordError = if (passwordValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(passwordValidationResult.error)
            } else {
                null
            }

            return DomainLoginErrors(
                emailError = emailError,
                passwordError = passwordError
            )
        }
    }
}