package com.klyukvin.ingoodhands.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.klyukvin.ingoodhands.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    feedScreen: @Composable () -> Unit,
    postScreen: @Composable (String) -> Unit
) {
    navigation(
        route = Screen.Home.route,
        startDestination = Screen.Feed.route
    ) {
        composable(Screen.Feed.route) {
            feedScreen()
        }
        composable(
            route = Screen.Post.route,
            arguments = listOf(
                navArgument(Screen.KEY_POST_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString(Screen.KEY_POST_ID).orEmpty()
            postScreen(postId)
        }
    }
}