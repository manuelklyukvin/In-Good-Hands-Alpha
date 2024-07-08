package com.klyukvin.ingoodhands.ui.screen.main.menu.account

sealed interface AccountState {

    data object Initial : AccountState
    data object Loading : AccountState
    data object Content : AccountState
}