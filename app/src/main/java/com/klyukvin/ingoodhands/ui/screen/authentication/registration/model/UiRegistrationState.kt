package com.klyukvin.ingoodhands.ui.screen.authentication.registration.model

sealed class UiRegistrationState {

    data object Initial : UiRegistrationState()
    data object Loading : UiRegistrationState()
    data object Content : UiRegistrationState()
}