package com.klyukvin.data.usecase.database.post.util

import com.klyukvin.data.common.util.PostReferences
import com.klyukvin.domain.usecase.database.post.model.DomainPostReferenceType
import com.klyukvin.domain.usecase.database.post.util.PostReferenceProvider

class PostReferenceProviderImpl : PostReferenceProvider {

    override fun getReference(postReferenceType: DomainPostReferenceType): String {
        return when (postReferenceType) {
            DomainPostReferenceType.ID -> PostReferences.Id.reference
            DomainPostReferenceType.USER_ID -> PostReferences.UserId.reference
            DomainPostReferenceType.IMAGES -> PostReferences.Images.reference
            DomainPostReferenceType.TITLE -> PostReferences.Title.reference
            DomainPostReferenceType.DESCRIPTION -> PostReferences.Description.reference
            DomainPostReferenceType.CATEGORY -> PostReferences.Category.reference
            DomainPostReferenceType.PHONE_NUMBER -> PostReferences.PhoneNumber.reference
            DomainPostReferenceType.ADDRESS -> PostReferences.Address.reference
            DomainPostReferenceType.DATE -> PostReferences.Date.reference
        }
    }
}