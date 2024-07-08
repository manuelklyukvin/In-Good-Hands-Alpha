package com.klyukvin.ingoodhands.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.klyukvin.ingoodhands.common.util.shimmerEffect
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun LoadingPostField(width: Dp? = null, height: Dp = 20.dp) {
    Box(
        modifier = Modifier
            .height(height)
            .then(
                if (width != null) {
                    Modifier.width(width)
                } else {
                    Modifier.fillMaxWidth()
                }
            )
            .clip(Shapes.defaultRoundedShape)
            .shimmerEffect()
    )
}