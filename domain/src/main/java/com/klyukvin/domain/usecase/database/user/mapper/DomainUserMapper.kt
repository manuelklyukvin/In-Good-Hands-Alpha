package com.klyukvin.domain.usecase.database.user.mapper

import com.klyukvin.domain.common.model.DomainUser
import com.klyukvin.domain.usecase.database.model.DomainSnapshot
import com.klyukvin.domain.usecase.database.user.model.DomainRegistrationUserData
import com.klyukvin.domain.usecase.database.user.model.DomainUserReferenceType
import com.klyukvin.domain.usecase.database.user.util.UserReferenceProvider

class DomainUserMapper(
    userReferenceProvider: UserReferenceProvider
) {

    private val idReference = userReferenceProvider.getReference(DomainUserReferenceType.ID)
    private val usernameReference = userReferenceProvider.getReference(DomainUserReferenceType.USERNAME)
    private val firstnameReference = userReferenceProvider.getReference(DomainUserReferenceType.FIRSTNAME)
    private val lastnameReference = userReferenceProvider.getReference(DomainUserReferenceType.LASTNAME)
    private val emailReference = userReferenceProvider.getReference(DomainUserReferenceType.EMAIL)
    private val passwordReference = userReferenceProvider.getReference(DomainUserReferenceType.PASSWORD)
    private val isAdminReference = userReferenceProvider.getReference(DomainUserReferenceType.IS_ADMIN)

    fun mapToUser(userSnapshot: DomainSnapshot): DomainUser {
        with(userSnapshot) {
            return DomainUser(
                id = getStringValue(idReference),
                username = getStringValue(usernameReference),
                firstname = getStringValue(firstnameReference),
                lastname = getStringValue(lastnameReference),
                email = getStringValue(emailReference),
                password = getStringValue(passwordReference),
                isAdmin = getValue(
                    reference = isAdminReference,
                    valueType = Boolean::class.java
                ) ?: false
            )
        }
    }

    fun mapToFinalUserParameters(
        registrationUserData: DomainRegistrationUserData,
        userId: String
    ): Map<String, Any> {
        with(registrationUserData) {
            return mapOf(
                idReference to userId,
                usernameReference to username,
                emailReference to email,
                passwordReference to password,
                isAdminReference to false
            )
        }
    }
}