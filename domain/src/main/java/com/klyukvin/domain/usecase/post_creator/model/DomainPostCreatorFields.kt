package com.klyukvin.domain.usecase.post_creator.model

data class DomainPostCreatorFields(
    val imageStrings: List<String>,
    val title: String,
    val description: String,
    val category: DomainPostCreatorCategory,
    val phoneNumber: String,
    val address: String
)