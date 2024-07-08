package com.klyukvin.ingoodhands.common.model

data class UiUser(
    val id: String,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean
)