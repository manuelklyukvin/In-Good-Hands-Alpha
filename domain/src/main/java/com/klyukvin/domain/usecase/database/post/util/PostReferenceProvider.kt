package com.klyukvin.domain.usecase.database.post.util

import com.klyukvin.domain.usecase.database.post.model.DomainPostReferenceType

interface PostReferenceProvider {

    fun getReference(postReferenceType: DomainPostReferenceType): String
}