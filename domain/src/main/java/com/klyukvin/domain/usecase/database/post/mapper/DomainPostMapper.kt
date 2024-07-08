package com.klyukvin.domain.usecase.database.post.mapper

import com.klyukvin.domain.common.model.DomainPost
import com.klyukvin.domain.usecase.database.model.DomainSnapshot
import com.klyukvin.domain.usecase.database.post.model.DomainInitialPostParameters
import com.klyukvin.domain.usecase.database.post.model.DomainPostReferenceType
import com.klyukvin.domain.usecase.database.post.util.PostReferenceProvider

class DomainPostMapper(
    postReferenceProvider: PostReferenceProvider
) {

    private val idReference = postReferenceProvider.getReference(DomainPostReferenceType.ID)
    private val userIdReference = postReferenceProvider.getReference(DomainPostReferenceType.USER_ID)
    private val imagesReference = postReferenceProvider.getReference(DomainPostReferenceType.IMAGES)
    private val titleReference = postReferenceProvider.getReference(DomainPostReferenceType.TITLE)
    private val descriptorReference = postReferenceProvider.getReference(DomainPostReferenceType.DESCRIPTION)
    private val categoryReference = postReferenceProvider.getReference(DomainPostReferenceType.CATEGORY)
    private val phoneNumberReference = postReferenceProvider.getReference(DomainPostReferenceType.PHONE_NUMBER)
    private val addressReference = postReferenceProvider.getReference(DomainPostReferenceType.ADDRESS)
    private val dateReference = postReferenceProvider.getReference(DomainPostReferenceType.DATE)

    fun mapToPost(postSnapshot: DomainSnapshot): DomainPost {
        with(postSnapshot) {
            return DomainPost(
                id = getStringValue(idReference),
                userId = getStringValue(userIdReference),
                imageStrings = getStringValues(imagesReference),
                title = getStringValue(titleReference),
                description = getStringValue(descriptorReference),
                category = getStringValue(categoryReference),
                phoneNumber = getStringValue(phoneNumberReference),
                address = getStringValue(addressReference),
                date = getStringValue(dateReference)
            )
        }
    }

    fun mapToFinalPostParameters(
        initialPostParameters: DomainInitialPostParameters,
        id: String,
        userId: String,
        imageLinks: List<String>,
        date: String
    ): Map<String, Any> {
        with(initialPostParameters) {
            return mapOf(
                idReference to id,
                userIdReference to userId,
                imagesReference to imageLinks,
                titleReference to title,
                descriptorReference to description,
                categoryReference to category,
                phoneNumberReference to phoneNumber,
                addressReference to address,
                dateReference to date
            )
        }
    }
}