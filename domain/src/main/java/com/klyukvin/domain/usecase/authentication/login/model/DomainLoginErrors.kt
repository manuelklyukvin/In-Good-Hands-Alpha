package com.klyukvin.domain.usecase.authentication.login.model

data class DomainLoginErrors(
    val emailError: String?,
    val passwordError: String?
)