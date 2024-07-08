package com.klyukvin.ingoodhands.ui.theme.resource

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import com.klyukvin.ingoodhands.ui.theme.CustomTheme

object Shapes {

    val defaultRoundedShape: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(CustomTheme.shape.corners)
}