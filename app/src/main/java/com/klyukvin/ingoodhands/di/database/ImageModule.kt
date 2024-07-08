package com.klyukvin.ingoodhands.di.database

import com.klyukvin.domain.repository.ImageRepository
import com.klyukvin.domain.usecase.CompressImageUseCase
import com.klyukvin.domain.usecase.database.image.ConvertToCompressedImageBytesUseCase
import com.klyukvin.domain.usecase.database.image.GetImageLinkByNameUseCase
import com.klyukvin.domain.usecase.database.image.SaveImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ImageModule {

    @Provides
    fun provideConvertToCompressedImageBytesUseCase(
        compressImageUseCase: CompressImageUseCase
    ): ConvertToCompressedImageBytesUseCase {
        return ConvertToCompressedImageBytesUseCase(compressImageUseCase)
    }

    @Provides
    fun provideSaveImageUseCase(imageRepository: ImageRepository): SaveImageUseCase {
        return SaveImageUseCase(imageRepository)
    }

    @Provides
    fun provideGetImageLinkByNameUseCase(imageRepository: ImageRepository): GetImageLinkByNameUseCase {
        return GetImageLinkByNameUseCase(imageRepository)
    }
}