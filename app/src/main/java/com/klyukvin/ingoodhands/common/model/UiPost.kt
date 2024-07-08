package com.klyukvin.ingoodhands.common.model

import android.net.Uri

data class UiPost(
    val id: String,
    val user: UiUser,
    val imageUris: List<Uri>,
    val title: String,
    val description: String,
    val category: String,
    val phoneNumber: String,
    val address: String,
    val date: String
)