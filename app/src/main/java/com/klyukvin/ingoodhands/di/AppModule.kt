package com.klyukvin.ingoodhands.di

import com.klyukvin.domain.usecase.database.user.GetUserByIdUseCase
import com.klyukvin.ingoodhands.common.mapper.PostMapper
import com.klyukvin.ingoodhands.common.mapper.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun providePostMapper(
        getUserByIdUseCase: GetUserByIdUseCase,
        userMapper: UserMapper
    ): PostMapper {
        return PostMapper(
            getUserByIdUseCase = getUserByIdUseCase,
            userMapper = userMapper
        )
    }

    @Provides
    fun provideUserMapper(): UserMapper {
        return UserMapper()
    }
}