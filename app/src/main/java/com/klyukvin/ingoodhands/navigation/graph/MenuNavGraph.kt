package com.klyukvin.ingoodhands.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.ui.screen.main.menu.account.AccountScreen

fun NavGraphBuilder.menuNavGraph(
    settingsScreen: @Composable () -> Unit,
    postCreatorScreen: @Composable () -> Unit
) {
    navigation(
        route = Screen.Menu.route,
        startDestination = Screen.Settings.route
    ) {
        composable(Screen.Settings.route) {
            settingsScreen()
        }
        composable(Screen.PostCreator.route) {
            postCreatorScreen()
        }
        composable("account") {
            AccountScreen()
        }
    }
}