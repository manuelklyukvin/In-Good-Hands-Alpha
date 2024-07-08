package com.klyukvin.ingoodhands.ui.screen.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.klyukvin.ingoodhands.navigation.graph.AppNavGraph
import com.klyukvin.ingoodhands.ui.theme.CustomTheme

@Composable
fun AppScreen() {
    CustomTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = CustomTheme.color.primaryBackground
        ) {
            AppNavGraph()
        }
    }
}