package com.klyukvin.domain.usecase.database.user

import com.klyukvin.domain.common.model.DomainUser
import com.klyukvin.domain.repository.UserRepository

class GetCurrentUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): DomainUser? {
        return userRepository.getCurrentUser()
    }
}