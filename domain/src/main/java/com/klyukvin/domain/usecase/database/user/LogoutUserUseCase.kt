package com.klyukvin.domain.usecase.database.user

import com.klyukvin.domain.repository.UserRepository

class LogoutUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() {
        userRepository.logoutUser()
    }
}