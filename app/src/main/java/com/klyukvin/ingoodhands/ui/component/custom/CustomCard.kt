package com.klyukvin.ingoodhands.ui.component.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    useDefaultPaddings: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CustomTheme.color.secondaryBackground),
        shape = Shapes.defaultRoundedShape
    ) {
        Column(
            modifier = Modifier.then(
                if (useDefaultPaddings) {
                    Modifier.padding(CustomTheme.shape.largePadding)
                } else {
                    Modifier
                }
            ),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content
        )
    }
}