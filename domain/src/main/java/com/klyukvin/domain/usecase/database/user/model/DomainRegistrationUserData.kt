package com.klyukvin.domain.usecase.database.user.model

data class DomainRegistrationUserData(
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)