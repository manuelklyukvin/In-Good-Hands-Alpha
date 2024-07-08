package com.klyukvin.ingoodhands.di.post_creator

import android.content.Context
import com.klyukvin.data.usecase.post_creator.GetPostCreatorErrorUseCaseImpl
import com.klyukvin.domain.usecase.post_creator.ArePostCreatorFieldsNotEmptyUseCase
import com.klyukvin.domain.usecase.post_creator.ArePostCreatorFieldsValidUseCase
import com.klyukvin.domain.usecase.post_creator.CheckSelectedImagesCountUseCase
import com.klyukvin.domain.usecase.post_creator.GetPostCreatorErrorUseCase
import com.klyukvin.domain.usecase.post_creator.PostCreatorValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class PostCreatorModule {

    @Provides
    fun provideGetPostCreatorErrorUseCase(
        @ApplicationContext context: Context
    ): GetPostCreatorErrorUseCase {
        return GetPostCreatorErrorUseCaseImpl(context)
    }

    @Provides
    fun providePostCreatorValidationUseCase(
        getPostCreatorErrorUseCase: GetPostCreatorErrorUseCase
    ): PostCreatorValidationUseCase {
        return PostCreatorValidationUseCase(getPostCreatorErrorUseCase)
    }

    @Provides
    fun provideCheckSelectedImagesCountUseCase(): CheckSelectedImagesCountUseCase {
        return CheckSelectedImagesCountUseCase()
    }

    @Provides
    fun provideArePostCreatorFieldsValidUseCase(): ArePostCreatorFieldsValidUseCase {
        return ArePostCreatorFieldsValidUseCase()
    }

    @Provides
    fun provideArePostCreatorFieldsNotEmptyUseCase(): ArePostCreatorFieldsNotEmptyUseCase {
        return ArePostCreatorFieldsNotEmptyUseCase()
    }
}