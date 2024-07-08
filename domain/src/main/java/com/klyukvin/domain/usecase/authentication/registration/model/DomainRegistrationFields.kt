package com.klyukvin.domain.usecase.authentication.registration.model

data class DomainRegistrationFields(
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)