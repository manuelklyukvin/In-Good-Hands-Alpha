package com.klyukvin.ingoodhands.ui.screen.authentication.registration.model

data class UiRegistrationErrors(
    val usernameError: String? = null,
    val firstnameError: String? = null,
    val lastnameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null
)