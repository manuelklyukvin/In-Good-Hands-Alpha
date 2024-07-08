package com.klyukvin.ingoodhands.ui.screen.introduction

import androidx.lifecycle.ViewModel
import com.klyukvin.ingoodhands.navigation.NavigationState
import com.klyukvin.ingoodhands.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor() : ViewModel() {

    fun onStartButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.Login.route)
    }
}