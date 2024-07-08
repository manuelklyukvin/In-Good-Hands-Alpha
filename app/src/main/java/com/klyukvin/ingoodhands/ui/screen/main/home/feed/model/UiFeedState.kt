package com.klyukvin.ingoodhands.ui.screen.main.home.feed.model

import com.klyukvin.ingoodhands.common.model.UiPost

sealed class UiFeedState {

    data object Initial : UiFeedState()
    data object Loading : UiFeedState()
    data class Content(val postList: List<UiPost>) : UiFeedState()
}