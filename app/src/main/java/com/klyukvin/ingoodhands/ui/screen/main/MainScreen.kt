package com.klyukvin.ingoodhands.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.navigation.graph.MainNavGraph
import com.klyukvin.ingoodhands.ui.component.custom.CustomIcon
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.LocalMainNavigationState
import com.klyukvin.ingoodhands.ui.theme.resource.Colors
import com.klyukvin.ingoodhands.ui.theme.resource.Fonts

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CustomTheme.color.primaryBackground,
        bottomBar = {
            BottomBar()
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MainNavGraph()
        }
    }
}

@Composable
private fun BottomBar() {
    Column {
        Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))
        HorizontalDivider(color = Colors.gray)
        NavigationBar(
            modifier = Modifier.height(CustomTheme.shape.mediumSize),
            containerColor = CustomTheme.color.primaryBackground
        ) {
            BottomBarItem(
                icon = painterResource(id = R.drawable.home),
                label = stringResource(id = R.string.main_bottom_bar_home),
                route = Screen.Home.route,
                mainRoute = Screen.Feed.route
            )
            BottomBarItem(
                icon = painterResource(id = R.drawable.menu),
                label = stringResource(id = R.string.main_bottom_bar_menu),
                route = Screen.Menu.route,
                mainRoute = Screen.Settings.route
            )
        }
    }
}

@Composable
private fun RowScope.BottomBarItem(
    icon: Painter,
    label: String,
    route: String,
    mainRoute: String
) {
    val navigationState = LocalMainNavigationState.current
    val navBackStackEntry by navigationState.navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route
    val selected = navBackStackEntry?.destination?.hierarchy?.any { destination ->
        destination.route == route
    } ?: false

    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = CustomTheme.color.primaryBackground,
            selectedIconColor = CustomTheme.color.accent,
            unselectedIconColor = Colors.black,
            selectedTextColor = CustomTheme.color.accent,
            unselectedTextColor = Colors.black
        ),
        icon = {
            CustomIcon(model = icon)
        },
        label = {
            Text(
                text = label,
                fontFamily = Fonts.ptSans
            )
        },
        onClick = {
            if (currentRoute != mainRoute) {
                navigationState.navigate(route)
            }
        },
        selected = selected
    )
}