package com.klyukvin.domain.usecase.authentication.registration

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.CommonValidateFieldUseCase
import com.klyukvin.domain.usecase.authentication.GetAuthenticationErrorUseCase
import com.klyukvin.domain.usecase.authentication.ValidateEmailUseCase
import com.klyukvin.domain.usecase.authentication.ValidatePasswordUseCase
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationFieldType
import com.klyukvin.domain.usecase.authentication.registration.model.DomainRegistrationErrors
import com.klyukvin.domain.usecase.authentication.registration.model.DomainRegistrationFields

class RegistrationValidationUseCase(
    private val getAuthenticationErrorUseCase: GetAuthenticationErrorUseCase,
    private val commonValidateFieldUseCase: CommonValidateFieldUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) {

    operator fun invoke(fields: DomainRegistrationFields): DomainRegistrationErrors {
        with(fields) {
            val usernameValidationResult = validateUsername(username)
            val firstnameValidationResult = validateName(firstname)
            val lastnameValidationResult = validateName(lastname)
            val nameFieldsValidationResult = validateNameFields(firstname, lastname)
            val emailValidationResult = validateEmailUseCase(email)
            val passwordValidationResult = validatePasswordUseCase(password)
            val confirmPasswordValidationResult = validatePasswordUseCase(confirmPassword)
            val passwordFieldsValidationResult = validatePasswordFields(password, confirmPassword)

            val usernameError = if (usernameValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(usernameValidationResult.error)
            } else {
                null
            }
            val firstnameError = if (firstnameValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(firstnameValidationResult.error)
            } else if (nameFieldsValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(nameFieldsValidationResult.error)
            } else {
                null
            }
            val lastnameError = if (lastnameValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(lastnameValidationResult.error)
            } else if (nameFieldsValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(nameFieldsValidationResult.error)
            } else {
                null
            }

            val emailError = if (emailValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(emailValidationResult.error)
            } else {
                null
            }

            val passwordError = if (passwordValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(passwordValidationResult.error)
            } else if (passwordFieldsValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(passwordFieldsValidationResult.error)
            } else {
                null
            }
            val confirmPasswordError = if (confirmPasswordValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(confirmPasswordValidationResult.error)
            } else if (passwordFieldsValidationResult is Result.Error) {
                getAuthenticationErrorUseCase(passwordFieldsValidationResult.error)
            } else {
                null
            }

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

    private fun validateUsername(username: String): Result<Unit, DomainAuthenticationErrorType> {
        return commonValidateFieldUseCase(username, DomainAuthenticationFieldType.USERNAME)
    }

    private fun validateName(name: String): Result<Unit, DomainAuthenticationErrorType> {
        val validationResult = commonValidateFieldUseCase(name, DomainAuthenticationFieldType.NAME)
        val areContainsSameLayout = isContainsOnlyLatinCharacters(name) || isContainsOnlyCyrillicCharacters(name)

        if (validationResult !is Result.Success) {
            return validationResult
        }
        if (!areContainsSameLayout) {
            return Result.Error(DomainAuthenticationErrorType.NAME_FIELD_CONTAINS_DIFFERENT_LAYOUTS)
        }
        return Result.Success(Unit)
    }

    private fun validateNameFields(
        firstname: String,
        lastname: String
    ): Result<Unit, DomainAuthenticationErrorType> {
        if (isContainsOnlyLatinCharacters(firstname) != isContainsOnlyLatinCharacters(lastname)) {
            return Result.Error(DomainAuthenticationErrorType.NAME_FIELDS_CONTAIN_DIFFERENT_LAYOUTS)
        }
        return Result.Success(Unit)
    }

    private fun validatePasswordFields(
        password: String,
        confirmPassword: String
    ): Result<Unit, DomainAuthenticationErrorType> {
        if (password != confirmPassword) {
            return Result.Error(DomainAuthenticationErrorType.PASSWORDS_ARE_NOT_EQUAL)
        }
        return Result.Success(Unit)
    }

    private fun isContainsOnlyLatinCharacters(value: String): Boolean {
        return value.all {
            it in 'A'..'Z' || it in 'a'..'z'
        }
    }

    private fun isContainsOnlyCyrillicCharacters(value: String): Boolean {
        return value.all {
            it in 'А'..'Я' || it in 'а'..'я'
        }
    }
}