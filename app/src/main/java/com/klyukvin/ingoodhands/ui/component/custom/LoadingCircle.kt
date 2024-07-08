package com.klyukvin.ingoodhands.ui.component.custom

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.klyukvin.ingoodhands.ui.theme.CustomTheme

@Composable
fun LoadingCircle(
    modifier: Modifier = Modifier.size(20.dp),
    color: Color = CustomTheme.color.primaryDisabledButtonContent
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = 3.dp,
        strokeCap = StrokeCap.Round
    )
}