package com.klyukvin.ingoodhands.di

import com.klyukvin.domain.usecase.GetCurrentDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetCurrentDateUseCase(): GetCurrentDateUseCase {
        return GetCurrentDateUseCase()
    }
}