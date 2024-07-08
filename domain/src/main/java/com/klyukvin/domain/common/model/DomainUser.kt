package com.klyukvin.domain.common.model

data class DomainUser(
    val id: String,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean
)