package com.klyukvin.ingoodhands.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import com.klyukvin.ingoodhands.common.util.AnimatedNavHost
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.ui.screen.authentication.login.LoginScreen
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.RegistrationScreen
import com.klyukvin.ingoodhands.ui.screen.introduction.IntroductionScreen
import com.klyukvin.ingoodhands.ui.screen.main.MainScreen
import com.klyukvin.ingoodhands.ui.theme.LocalAppNavigationState

@Composable
fun AppNavGraph() {
    val navigationState = LocalAppNavigationState.current

    AnimatedNavHost(
        navController = navigationState.navController,
        startDestination = Screen.Introduction.route
    ) {
        composable(Screen.Introduction.route) {
            IntroductionScreen()
        }
        composable(Screen.Login.route) {
            LoginScreen()
        }
        composable(Screen.Registration.route) {
            RegistrationScreen()
        }
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}