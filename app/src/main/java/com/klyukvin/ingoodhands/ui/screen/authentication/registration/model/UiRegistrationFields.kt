package com.klyukvin.ingoodhands.ui.screen.authentication.registration.model

data class UiRegistrationFields(
    val username: String = "",
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)