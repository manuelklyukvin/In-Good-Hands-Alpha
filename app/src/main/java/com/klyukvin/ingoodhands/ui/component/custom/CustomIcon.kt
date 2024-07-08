package com.klyukvin.ingoodhands.ui.component.custom

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    model: Painter,
    tint: Color? = null
) {
    Icon(
        modifier = modifier,
        painter = model,
        contentDescription = null,
        tint = tint ?: LocalContentColor.current
    )
}