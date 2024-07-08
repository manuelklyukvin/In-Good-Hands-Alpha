package com.klyukvin.ingoodhands.di.authentication

import com.klyukvin.ingoodhands.ui.screen.authentication.login.mapper.LoginErrorsMapper
import com.klyukvin.ingoodhands.ui.screen.authentication.login.mapper.LoginFieldsMapper
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.mapper.RegistrationErrorsMapper
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.mapper.RegistrationFieldsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AuthenticationMapperModule {

    @Provides
    fun provideLoginFieldsMapper(): LoginFieldsMapper {
        return LoginFieldsMapper()
    }

    @Provides
    fun provideLoginErrorsMapper(): LoginErrorsMapper {
        return LoginErrorsMapper()
    }

    @Provides
    fun provideRegistrationFieldsMapper(): RegistrationFieldsMapper {
        return RegistrationFieldsMapper()
    }

    @Provides
    fun provideRegistrationErrorsMapper(): RegistrationErrorsMapper {
        return RegistrationErrorsMapper()
    }
}