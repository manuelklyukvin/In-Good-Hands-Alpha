package com.klyukvin.ingoodhands.di.authentication

import android.content.Context
import com.klyukvin.data.usecase.authentication.GetAuthenticationErrorUseCaseImpl
import com.klyukvin.domain.usecase.authentication.CommonValidateFieldUseCase
import com.klyukvin.domain.usecase.authentication.GetAuthenticationErrorUseCase
import com.klyukvin.domain.usecase.authentication.ValidateEmailUseCase
import com.klyukvin.domain.usecase.authentication.ValidatePasswordUseCase
import com.klyukvin.domain.usecase.authentication.login.AreLoginFieldsNotEmptyUseCase
import com.klyukvin.domain.usecase.authentication.login.AreLoginFieldsValidUseCase
import com.klyukvin.domain.usecase.authentication.login.LoginValidationUseCase
import com.klyukvin.domain.usecase.authentication.registration.AreRegistrationFieldsNotEmptyUseCase
import com.klyukvin.domain.usecase.authentication.registration.AreRegistrationFieldsValidUseCase
import com.klyukvin.domain.usecase.authentication.registration.RegistrationValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class AuthenticationModule {

    @Provides
    fun provideGetAuthenticationErrorUseCase(
        @ApplicationContext context: Context
    ): GetAuthenticationErrorUseCase {
        return GetAuthenticationErrorUseCaseImpl(context)
    }

    @Provides
    fun provideCommonValidateFieldUseCase(): CommonValidateFieldUseCase {
        return CommonValidateFieldUseCase()
    }

    @Provides
    fun provideValidateEmailUseCase(
        commonValidateFieldUseCase: CommonValidateFieldUseCase
    ): ValidateEmailUseCase {
        return ValidateEmailUseCase(commonValidateFieldUseCase)
    }

    @Provides
    fun provideValidatePasswordUseCase(
        commonValidateFieldUseCase: CommonValidateFieldUseCase
    ): ValidatePasswordUseCase {
        return ValidatePasswordUseCase(commonValidateFieldUseCase)
    }

    @Provides
    fun provideLoginValidationUseCase(
        getAuthenticationErrorUseCase: GetAuthenticationErrorUseCase,
        validateEmailUseCase: ValidateEmailUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase
    ): LoginValidationUseCase {
        return LoginValidationUseCase(
            getAuthenticationErrorUseCase = getAuthenticationErrorUseCase,
            validateEmailUseCase = validateEmailUseCase,
            validatePasswordUseCase = validatePasswordUseCase
        )
    }

    @Provides
    fun provideAreLoginFieldsValidUseCase(): AreLoginFieldsValidUseCase {
        return AreLoginFieldsValidUseCase()
    }

    @Provides
    fun provideAreLoginFieldsNotEmptyUseCase(): AreLoginFieldsNotEmptyUseCase {
        return AreLoginFieldsNotEmptyUseCase()
    }

    @Provides
    fun provideRegistrationValidationUseCase(
        getAuthenticationErrorUseCase: GetAuthenticationErrorUseCase,
        commonValidateFieldUseCase: CommonValidateFieldUseCase,
        validateEmailUseCase: ValidateEmailUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase
    ): RegistrationValidationUseCase {
        return RegistrationValidationUseCase(
            getAuthenticationErrorUseCase = getAuthenticationErrorUseCase,
            commonValidateFieldUseCase = commonValidateFieldUseCase,
            validateEmailUseCase = validateEmailUseCase,
            validatePasswordUseCase = validatePasswordUseCase
        )
    }

    @Provides
    fun provideAreRegistrationFieldsValidUseCase(): AreRegistrationFieldsValidUseCase {
        return AreRegistrationFieldsValidUseCase()
    }

    @Provides
    fun provideAreRegistrationFieldsNotEmptyUseCase(): AreRegistrationFieldsNotEmptyUseCase {
        return AreRegistrationFieldsNotEmptyUseCase()
    }
}