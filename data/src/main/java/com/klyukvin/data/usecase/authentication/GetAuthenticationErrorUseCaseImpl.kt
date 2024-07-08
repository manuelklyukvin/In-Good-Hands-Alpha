package com.klyukvin.data.usecase.authentication

import android.content.Context
import com.klyukvin.data.R
import com.klyukvin.domain.usecase.authentication.GetAuthenticationErrorUseCase
import com.klyukvin.domain.usecase.authentication.model.DomainAuthenticationErrorType

class GetAuthenticationErrorUseCaseImpl(
    private val context: Context
) : GetAuthenticationErrorUseCase {

    override operator fun invoke(errorType: DomainAuthenticationErrorType?): String? {
        with(context) {
             return when (errorType) {
                DomainAuthenticationErrorType.FIELD_CHARACTERS_ARE_NOT_ACCEPTABLE -> getString(R.string.authentication_field_characters_are_not_acceptable)
                DomainAuthenticationErrorType.FIELD_IS_TOO_SHORT -> getString(R.string.authentication_field_is_too_short)
                DomainAuthenticationErrorType.FIELD_IS_TOO_LONG -> getString(R.string.authentication_field_is_too_long)
                DomainAuthenticationErrorType.NAME_FIELD_CONTAINS_DIFFERENT_LAYOUTS -> getString(R.string.authentication_name_field_contains_different_layouts)
                DomainAuthenticationErrorType.NAME_FIELDS_CONTAIN_DIFFERENT_LAYOUTS -> getString(R.string.authentication_name_fields_contain_different_layouts)
                DomainAuthenticationErrorType.EMAIL_IS_INVALID -> getString(R.string.authentication_email_is_invalid)
                DomainAuthenticationErrorType.PASSWORDS_ARE_NOT_EQUAL -> getString(R.string.authentication_passwords_are_not_equal)
                else -> null
            }
        }
    }
}