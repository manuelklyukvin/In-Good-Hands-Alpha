package com.klyukvin.domain.usecase.database.post.model

data class DomainInitialPostParameters(
    val imageStrings: List<String>,
    val title: String,
    val description: String,
    val category: String,
    val phoneNumber: String,
    val address: String
)