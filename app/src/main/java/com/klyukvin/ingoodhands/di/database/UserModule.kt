package com.klyukvin.ingoodhands.di.database

import android.content.Context
import com.klyukvin.data.usecase.database.user.GetUserErrorUseCaseImpl
import com.klyukvin.data.usecase.database.user.util.UserReferenceProviderImpl
import com.klyukvin.domain.repository.UserRepository
import com.klyukvin.domain.usecase.database.user.GetCurrentUserUseCase
import com.klyukvin.domain.usecase.database.user.GetUserByIdUseCase
import com.klyukvin.domain.usecase.database.user.GetUserErrorUseCase
import com.klyukvin.domain.usecase.database.user.LoginUserUseCase
import com.klyukvin.domain.usecase.database.user.LogoutUserUseCase
import com.klyukvin.domain.usecase.database.user.RegisterUserUseCase
import com.klyukvin.domain.usecase.database.user.mapper.DomainUserMapper
import com.klyukvin.domain.usecase.database.user.util.UserReferenceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class UserModule {

    @Provides
    fun provideGetCurrentUserUseCase(userRepository: UserRepository): GetCurrentUserUseCase {
        return GetCurrentUserUseCase(userRepository)
    }

    @Provides
    fun provideGetUserByIdUseCase(userRepository: UserRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(userRepository)
    }

    @Provides
    fun provideLoginUserUseCase(userRepository: UserRepository): LoginUserUseCase {
        return LoginUserUseCase(userRepository)
    }

    @Provides
    fun provideLogoutUserUseCase(userRepository: UserRepository): LogoutUserUseCase {
        return LogoutUserUseCase(userRepository)
    }

    @Provides
    fun provideRegisterUserUseCase(userRepository: UserRepository): RegisterUserUseCase {
        return RegisterUserUseCase(userRepository)
    }

    @Provides
    fun provideDomainUserMapper(userReferenceProvider: UserReferenceProvider): DomainUserMapper {
        return DomainUserMapper(userReferenceProvider)
    }

    @Provides
    fun provideUserReferenceProvider(): UserReferenceProvider {
        return UserReferenceProviderImpl()
    }

    @Provides
    fun provideGetUserErrorUseCase(@ApplicationContext context: Context): GetUserErrorUseCase {
        return GetUserErrorUseCaseImpl(context)
    }
}