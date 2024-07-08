package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model

sealed class UiPostCreatorState {

    data object Initial : UiPostCreatorState()
    data object Loading : UiPostCreatorState()
    data object Content : UiPostCreatorState()
}