package com.klyukvin.ingoodhands.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.klyukvin.ingoodhands.common.util.noIndicationClickable

@Composable
fun ScreenBlocking() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .noIndicationClickable {}
    )
}