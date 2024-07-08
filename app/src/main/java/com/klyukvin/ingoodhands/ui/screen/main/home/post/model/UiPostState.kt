package com.klyukvin.ingoodhands.ui.screen.main.home.post.model

import com.klyukvin.ingoodhands.common.model.UiPost

sealed class UiPostState {

    data object Initial : UiPostState()
    data object Loading : UiPostState()
    data class Content(val post: UiPost) : UiPostState()
}