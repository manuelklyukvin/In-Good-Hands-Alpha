package com.klyukvin.domain.usecase.database.user

import com.klyukvin.domain.common.model.DomainUser
import com.klyukvin.domain.repository.UserRepository

class GetUserByIdUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(userId: String): DomainUser {
        return userRepository.getUserById(userId)
    }
}