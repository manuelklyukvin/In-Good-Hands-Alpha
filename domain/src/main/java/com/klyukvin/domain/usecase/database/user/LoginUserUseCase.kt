package com.klyukvin.domain.usecase.database.user

import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.repository.UserRepository
import com.klyukvin.domain.usecase.database.user.model.DomainLoginUserData
import com.klyukvin.domain.usecase.database.user.model.DomainUserErrorType

class LoginUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(loginUserData: DomainLoginUserData): Result<Unit, DomainUserErrorType> {
        return userRepository.loginUser(loginUserData)
    }
}