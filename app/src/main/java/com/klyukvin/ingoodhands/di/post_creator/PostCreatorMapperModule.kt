package com.klyukvin.ingoodhands.di.post_creator

import android.content.Context
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.mapper.PostCreatorCategoryMapper
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.mapper.PostCreatorErrorsMapper
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.mapper.PostCreatorFieldsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class PostCreatorMapperModule {

    @Provides
    fun providePostCreatorFieldsMapper(
        @ApplicationContext context: Context,
        postCreatorCategoryMapper: PostCreatorCategoryMapper
    ): PostCreatorFieldsMapper {
        return PostCreatorFieldsMapper(
            context = context,
            postCreatorCategoryMapper = postCreatorCategoryMapper
        )
    }

    @Provides
    fun providePostCreatorErrorsMapper(): PostCreatorErrorsMapper {
        return PostCreatorErrorsMapper()
    }

    @Provides
    fun providePostCategoryMapper(): PostCreatorCategoryMapper {
        return PostCreatorCategoryMapper()
    }
}