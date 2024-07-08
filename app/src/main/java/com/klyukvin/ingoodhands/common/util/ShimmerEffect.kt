package com.klyukvin.ingoodhands.common.util

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.klyukvin.ingoodhands.ui.theme.resource.Colors

fun Modifier.shimmerEffect() = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition(label = "")
    val colorsList = listOf(Colors.gray, Colors.lightGray, Colors.gray)
    val duration = 1000

    val startOffsetX by transition.animateFloat(
        initialValue = size.width.toFloat() * -2,
        targetValue = size.width.toFloat() * 2,
        animationSpec = infiniteRepeatable(
            animation = tween(duration)
        ),
        label = ""
    )
    val startOffsetY = 0f

    val endOffsetX = startOffsetX + size.width.toFloat()
    val endOffsetY = 0f

    background(
        brush = Brush.linearGradient(
            colors = colorsList,
            start = Offset(
                x = startOffsetX,
                y = startOffsetY
            ),
            end = Offset(
                x = endOffsetX,
                y = endOffsetY
            )
        )
    ).onGloballyPositioned {
        size = it.size
    }
}