package com.klyukvin.domain.usecase.database.user.util

import com.klyukvin.domain.usecase.database.user.model.DomainUserReferenceType

interface UserReferenceProvider {

    fun getReference(userReferenceType: DomainUserReferenceType): String
}