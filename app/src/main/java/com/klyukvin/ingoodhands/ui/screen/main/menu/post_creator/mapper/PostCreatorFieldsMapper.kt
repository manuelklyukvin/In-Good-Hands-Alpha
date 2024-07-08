package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.mapper

import android.content.Context
import com.klyukvin.domain.usecase.database.post.model.DomainInitialPostParameters
import com.klyukvin.domain.usecase.post_creator.model.DomainPostCreatorFields
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorFields

class PostCreatorFieldsMapper(
    private val context: Context,
    private val postCreatorCategoryMapper: PostCreatorCategoryMapper
) {

    fun mapToDomain(fields: UiPostCreatorFields): DomainPostCreatorFields {
        with(fields) {
            return DomainPostCreatorFields(
                imageStrings = imageUris.map {
                    it.toString()
                },
                title = title,
                description = description,
                category = postCreatorCategoryMapper.mapToDomain(category),
                phoneNumber = phoneNumber,
                address = address
            )
        }
    }

    fun mapToInitialPostParameters(fields: UiPostCreatorFields): DomainInitialPostParameters {
        with(fields) {
            return DomainInitialPostParameters(
                imageStrings = imageUris.map {
                    it.toString()
                },
                title = title,
                description = description,
                category = context.getString(category.nameId),
                phoneNumber = phoneNumber,
                address = address
            )
        }
    }
}