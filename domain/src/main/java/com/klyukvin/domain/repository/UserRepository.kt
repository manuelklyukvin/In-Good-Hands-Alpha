package com.klyukvin.domain.repository

import com.klyukvin.domain.common.model.DomainUser
import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.database.user.model.DomainLoginUserData
import com.klyukvin.domain.usecase.database.user.model.DomainRegistrationUserData
import com.klyukvin.domain.usecase.database.user.model.DomainUserErrorType

interface UserRepository {

    suspend fun getCurrentUser(): DomainUser?

    suspend fun getUserById(userId: String): DomainUser

    suspend fun loginUser(loginUserData: DomainLoginUserData): Result<Unit, DomainUserErrorType>

    suspend fun logoutUser()

    suspend fun registerUser(registrationUserData: DomainRegistrationUserData)
}