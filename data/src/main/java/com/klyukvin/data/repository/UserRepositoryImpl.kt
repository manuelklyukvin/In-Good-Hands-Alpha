package com.klyukvin.data.repository

import com.klyukvin.domain.common.model.DomainUser
import com.klyukvin.domain.common.model.dao.UserDAO
import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.repository.UserRepository
import com.klyukvin.domain.usecase.database.user.model.DomainLoginUserData
import com.klyukvin.domain.usecase.database.user.model.DomainRegistrationUserData
import com.klyukvin.domain.usecase.database.user.model.DomainUserErrorType

class UserRepositoryImpl(
    private val userDao: UserDAO
) : UserRepository {

    override suspend fun getCurrentUser(): DomainUser? {
        return userDao.getCurrentUser()
    }

    override suspend fun getUserById(userId: String): DomainUser {
        return userDao.getUserById(userId)
    }

    override suspend fun loginUser(loginUserData: DomainLoginUserData): Result<Unit, DomainUserErrorType> {
        return userDao.loginUser(loginUserData)
    }

    override suspend fun logoutUser() {
        userDao.logoutUser()
    }

    override suspend fun registerUser(registrationUserData: DomainRegistrationUserData) {
        userDao.registerUser(registrationUserData)
    }
}