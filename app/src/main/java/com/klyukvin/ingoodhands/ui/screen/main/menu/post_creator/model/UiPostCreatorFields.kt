package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model

import android.net.Uri

data class UiPostCreatorFields(
    val imageUris: List<Uri> = emptyList(),
    val title: String = "",
    val description: String = "",
    val category: UiPostCreatorCategory = UiPostCreatorCategory.NotSelected,
    val phoneNumber: String = "",
    val address: String = ""
)