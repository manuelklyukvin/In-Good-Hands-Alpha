package com.klyukvin.domain.usecase.database.user

import com.klyukvin.domain.repository.UserRepository
import com.klyukvin.domain.usecase.database.user.model.DomainRegistrationUserData

class RegisterUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(registrationUserData: DomainRegistrationUserData) {
        userRepository.registerUser(registrationUserData)
    }
}