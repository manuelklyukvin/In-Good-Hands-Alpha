package com.klyukvin.domain.usecase.authentication.registration.model

data class DomainRegistrationErrors(
    val usernameError: String?,
    val firstnameError: String?,
    val lastnameError: String?,
    val emailError: String?,
    val passwordError: String?,
    val confirmPasswordError: String?
)