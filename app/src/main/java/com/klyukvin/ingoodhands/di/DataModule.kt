package com.klyukvin.ingoodhands.di

import android.content.Context
import com.klyukvin.data.common.model.dao.FirebaseImageDAO
import com.klyukvin.data.common.model.dao.FirebasePostDAO
import com.klyukvin.data.common.model.dao.FirebaseUserDAO
import com.klyukvin.data.repository.ImageRepositoryImpl
import com.klyukvin.data.repository.PostRepositoryImpl
import com.klyukvin.data.repository.UserRepositoryImpl
import com.klyukvin.data.usecase.CompressImageUseCaseImpl
import com.klyukvin.domain.common.model.dao.ImageDAO
import com.klyukvin.domain.common.model.dao.PostDAO
import com.klyukvin.domain.common.model.dao.UserDAO
import com.klyukvin.domain.repository.ImageRepository
import com.klyukvin.domain.repository.PostRepository
import com.klyukvin.domain.repository.UserRepository
import com.klyukvin.domain.usecase.CompressImageUseCase
import com.klyukvin.domain.usecase.database.post.mapper.DomainPostMapper
import com.klyukvin.domain.usecase.database.user.mapper.DomainUserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun providePostDAO(domainPostMapper: DomainPostMapper): PostDAO {
        return FirebasePostDAO(domainPostMapper)
    }

    @Provides
    fun provideImageDAO(): ImageDAO {
        return FirebaseImageDAO()
    }

    @Provides
    fun provideUserDAO(domainUserMapper: DomainUserMapper): UserDAO {
        return FirebaseUserDAO(domainUserMapper)
    }

    @Provides
    fun providePostRepository(postDao: PostDAO): PostRepository {
        return PostRepositoryImpl(postDao)
    }

    @Provides
    fun provideImageRepository(imageDao: ImageDAO): ImageRepository {
        return ImageRepositoryImpl(imageDao)
    }

    @Provides
    fun provideUserRepository(userDao: UserDAO): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    fun provideCompressImageUseCase(@ApplicationContext context: Context): CompressImageUseCase {
        return CompressImageUseCaseImpl(context)
    }
}