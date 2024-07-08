package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model

data class UiPostCreatorErrors(
    val imagesError: String? = null,
    val titleError: String? = null,
    val descriptionError: String? = null,
    val categoryError: String? = null,
    val phoneNumberError: String? = null,
    val addressError: String? = null
)