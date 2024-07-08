package com.klyukvin.domain.usecase.authentication.model

import com.klyukvin.domain.common.util.Error

enum class DomainAuthenticationErrorType : Error {
    FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE,
    FIELD_IS_TOO_SHORT,
    FIELD_IS_TOO_LONG,
    NAME_FIELD_CONTAINS_DIFFERENT_LAYOUTS,
    NAME_FIELDS_CONTAIN_DIFFERENT_LAYOUTS,
    EMAIL_IS_INVALID,
    PASSWORDS_ARE_NOT_EQUAL
}