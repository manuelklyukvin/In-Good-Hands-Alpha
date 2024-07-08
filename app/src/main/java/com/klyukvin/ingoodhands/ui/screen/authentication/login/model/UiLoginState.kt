package com.klyukvin.ingoodhands.ui.screen.authentication.login.model

sealed class UiLoginState {

    data object Initial : UiLoginState()
    data object Loading : UiLoginState()
    data object Content : UiLoginState()
}