package com.klyukvin.ingoodhands.navigation.graph

import androidx.compose.runtime.Composable
import com.klyukvin.ingoodhands.common.util.AnimatedNavHost
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.ui.screen.main.home.feed.FeedScreen
import com.klyukvin.ingoodhands.ui.screen.main.home.post.PostScreen
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.PostCreatorScreen
import com.klyukvin.ingoodhands.ui.screen.main.menu.settings.SettingsScreen
import com.klyukvin.ingoodhands.ui.theme.LocalMainNavigationState

@Composable
fun MainNavGraph() {
    val navigationState = LocalMainNavigationState.current

    AnimatedNavHost(
        navController = navigationState.navController,
        startDestination = Screen.Home.route
    ) {
        homeNavGraph(
            feedScreen = {
                FeedScreen()
            },
            postScreen = { postId ->
                PostScreen(postId)
            }
        )
        menuNavGraph(
            settingsScreen = {
                SettingsScreen()
            },
            postCreatorScreen = {
                PostCreatorScreen()
            }
        )
    }
}