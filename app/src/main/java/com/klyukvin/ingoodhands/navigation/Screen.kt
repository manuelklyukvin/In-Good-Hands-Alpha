package com.klyukvin.ingoodhands.navigation

sealed class Screen(val route: String) {

    data object Introduction : Screen(INTRODUCTION_ROUTE)
    data object Login : Screen(LOGIN_ROUTE)
    data object Registration : Screen(REGISTRATION_ROUTE)

    data object Main : Screen(MAIN_ROUTE)

    data object Home : Screen(HOME_ROUTE)
    data object Feed : Screen(FEED_ROUTE)
    data object Post : Screen(POST_ARGS_ROUTE) {

        fun getRouteWithArgs(postId: String): String {
            return "$POST_ROUTE/$postId"
        }
    }

    data object Menu : Screen(MENU_ROUTE)
    data object Settings : Screen(SETTINGS_ROUTE)
    data object PostCreator : Screen(POST_CREATOR_ROUTE)

    companion object {

        private const val INTRODUCTION_ROUTE = "introduction"
        private const val LOGIN_ROUTE = "login"
        private const val REGISTRATION_ROUTE = "registration"

        private const val MAIN_ROUTE = "main"

        private const val HOME_ROUTE = "home"
        private const val FEED_ROUTE = "feed"
        private const val POST_ROUTE = "post"
        const val KEY_POST_ID = "post_id"
        private const val POST_ARGS_ROUTE = "$POST_ROUTE/{$KEY_POST_ID}"

        private const val MENU_ROUTE = "menu"
        private const val SETTINGS_ROUTE = "settings"
        private const val POST_CREATOR_ROUTE = "post_creator"
    }
}