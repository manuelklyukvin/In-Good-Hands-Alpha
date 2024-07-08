package com.klyukvin.data.usecase.database.user.util

import com.klyukvin.data.common.util.UserReferences
import com.klyukvin.domain.usecase.database.user.model.DomainUserReferenceType
import com.klyukvin.domain.usecase.database.user.util.UserReferenceProvider

class UserReferenceProviderImpl : UserReferenceProvider {

    override fun getReference(userReferenceType: DomainUserReferenceType): String {
        return when (userReferenceType) {
            DomainUserReferenceType.ID -> UserReferences.Id.reference
            DomainUserReferenceType.USERNAME -> UserReferences.Username.reference
            DomainUserReferenceType.FIRSTNAME -> UserReferences.Firstname.reference
            DomainUserReferenceType.LASTNAME -> UserReferences.Lastname.reference
            DomainUserReferenceType.EMAIL -> UserReferences.Email.reference
            DomainUserReferenceType.PASSWORD -> UserReferences.Password.reference
            DomainUserReferenceType.IS_ADMIN -> UserReferences.IsAdmin.reference
        }
    }
}