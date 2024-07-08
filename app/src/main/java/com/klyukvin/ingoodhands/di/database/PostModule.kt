package com.klyukvin.ingoodhands.di.database

import com.klyukvin.data.usecase.database.post.util.PostReferenceProviderImpl
import com.klyukvin.domain.repository.PostRepository
import com.klyukvin.domain.usecase.GetCurrentDateUseCase
import com.klyukvin.domain.usecase.database.image.ConvertToCompressedImageBytesUseCase
import com.klyukvin.domain.usecase.database.image.GetImageLinkByNameUseCase
import com.klyukvin.domain.usecase.database.image.SaveImageUseCase
import com.klyukvin.domain.usecase.database.post.GeneratePostIdUseCase
import com.klyukvin.domain.usecase.database.post.GetPageCountUseCase
import com.klyukvin.domain.usecase.database.post.LoadPostByIdUseCase
import com.klyukvin.domain.usecase.database.post.LoadPostsOnPageUseCase
import com.klyukvin.domain.usecase.database.post.SavePostUseCase
import com.klyukvin.domain.usecase.database.post.mapper.DomainPostMapper
import com.klyukvin.domain.usecase.database.post.util.PostReferenceProvider
import com.klyukvin.domain.usecase.database.user.GetCurrentUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PostModule {

    @Provides
    fun provideLoadPostsOnPageUseCase(postRepository: PostRepository): LoadPostsOnPageUseCase {
        return LoadPostsOnPageUseCase(postRepository)
    }

    @Provides
    fun provideLoadPostByIdUseCase(postRepository: PostRepository): LoadPostByIdUseCase {
        return LoadPostByIdUseCase(postRepository)
    }

    @Provides
    fun provideSavePostUseCase(
        postRepository: PostRepository,
        generatePostIdUseCase: GeneratePostIdUseCase,
        getCurrentUserUseCase: GetCurrentUserUseCase,
        convertToCompressedImageBytesUseCase: ConvertToCompressedImageBytesUseCase,
        saveImageUseCase: SaveImageUseCase,
        getImageLinkByNameUseCase: GetImageLinkByNameUseCase,
        domainPostMapper: DomainPostMapper,
        getCurrentDateUseCase: GetCurrentDateUseCase
    ): SavePostUseCase {
        return SavePostUseCase(
            postRepository = postRepository,
            generatePostIdUseCase = generatePostIdUseCase,
            getCurrentUserUseCase = getCurrentUserUseCase,
            convertToCompressedImageBytesUseCase = convertToCompressedImageBytesUseCase,
            saveImageUseCase = saveImageUseCase,
            getImageLinkByNameUseCase = getImageLinkByNameUseCase,
            domainPostMapper = domainPostMapper,
            getCurrentDateUseCase = getCurrentDateUseCase
        )
    }

    @Provides
    fun provideGeneratePostIdUseCase(postRepository: PostRepository): GeneratePostIdUseCase {
        return GeneratePostIdUseCase(postRepository)
    }

    @Provides
    fun provideGetPageCount(postRepository: PostRepository): GetPageCountUseCase {
        return GetPageCountUseCase(postRepository)
    }

    @Provides
    fun provideDomainPostMapper(postReferenceProvider: PostReferenceProvider): DomainPostMapper {
        return DomainPostMapper(postReferenceProvider)
    }

    @Provides
    fun providePostReferenceProvider(): PostReferenceProvider {
        return PostReferenceProviderImpl()
    }
}