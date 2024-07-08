package com.klyukvin.domain.usecase.authentication

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationFieldType

class CommonValidateFieldUseCase {

    operator fun invoke(
        field: String,
        fieldType: DomainAuthenticationFieldType
    ): Result<Unit, DomainAuthenticationErrorType> {
        val value = field.trim()

        if (!areCharactersAcceptable(value, fieldType)) {
            return Result.Error(DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE)
        }

        val (minLength, maxLength) = getFieldLengthRange(fieldType)
        if (value.length < minLength) {
            return Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_SHORT)
        }
        if (value.length > maxLength) {
            return Result.Error(DomainAuthenticationErrorType.FIELD_IS_TOO_LONG)
        }

        return Result.Success(Unit)
    }

    private fun areCharactersAcceptable(value: String, fieldType: DomainAuthenticationFieldType): Boolean {
        val acceptableCharacters = getFieldAcceptableCharacters(fieldType)
        return value.all {
            it in acceptableCharacters
        }
    }

    private fun getFieldAcceptableCharacters(fieldType: DomainAuthenticationFieldType): List<Char> {
        return when (fieldType) {
            DomainAuthenticationFieldType.USERNAME -> usernameCharacters
            DomainAuthenticationFieldType.NAME -> nameCharacters
            DomainAuthenticationFieldType.EMAIL -> emailCharacters
            DomainAuthenticationFieldType.PASSWORD -> passwordCharacters
        }
    }

    private fun getFieldLengthRange(fieldType: DomainAuthenticationFieldType): Pair<Int, Int> {
        return when (fieldType) {
            DomainAuthenticationFieldType.USERNAME -> MIN_USERNAME_LENGTH to MAX_USERNAME_LENGTH
            DomainAuthenticationFieldType.NAME -> MIN_NAME_LENGTH to MAX_NAME_LENGTH
            DomainAuthenticationFieldType.EMAIL -> MIN_EMAIL_LENGTH to MAX_EMAIL_LENGTH
            DomainAuthenticationFieldType.PASSWORD -> MIN_PASSWORD_LENGTH to MAX_PASSWORD_LENGTH
        }
    }

    private companion object {

        val usernameCharacters = ('0'..'9') + ('A'..'Z') + ('a'..'z') + ('А'..'Я') + ('а'..'я') + listOf('-', '_')
        val nameCharacters = ('A'..'Z') + ('a'..'z') + ('А'..'Я') + ('а'..'я')
        val emailCharacters = ('0'..'9') + ('A'..'Z') + ('a'..'z') + listOf('@', '.', '_')
        val passwordCharacters = ('0'..'9') + ('A'..'Z') + ('a'..'z') + listOf('-', '_')

        const val MIN_USERNAME_LENGTH = 3
        const val MAX_USERNAME_LENGTH = 20

        const val MIN_NAME_LENGTH = 2
        const val MAX_NAME_LENGTH = 30

        const val MIN_EMAIL_LENGTH = 5
        const val MAX_EMAIL_LENGTH = 30

        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 20
    }
}