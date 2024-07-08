package com.klyukvin.domain.common.model

data class DomainPost(
    val id: String,
    val userId: String,
    val imageStrings: List<String>,
    val title: String,
    val description: String,
    val category: String,
    val phoneNumber: String,
    val address: String,
    val date: String,
)