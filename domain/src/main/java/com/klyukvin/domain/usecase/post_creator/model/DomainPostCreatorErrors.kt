package com.klyukvin.domain.usecase.post_creator.model

data class DomainPostCreatorErrors(
    val imagesError: String?,
    val titleError: String?,
    val descriptionError: String?,
    val categoryError: String?,
    val phoneNumberError: String?,
    val addressError: String?
)