package com.klyukvin.ingoodhands.ui.screen.main.menu.settings.model

sealed class UiSettingsState {

    data object Initial : UiSettingsState()
    data object Loading : UiSettingsState()
    data object Content : UiSettingsState()
}