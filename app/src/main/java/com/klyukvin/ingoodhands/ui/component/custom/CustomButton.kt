package com.klyukvin.ingoodhands.ui.component.custom

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    contentColor: Color = CustomTheme.color.primaryButtonContent,
    containerColor: Color = CustomTheme.color.primaryButtonContainer,
    disabledContentColor: Color = CustomTheme.color.primaryDisabledButtonContent,
    disabledContainerColor: Color = CustomTheme.color.primaryDisabledButtonContainer,
    content: @Composable (() -> Unit)? = null
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor
        ),
        shape = Shapes.defaultRoundedShape,
        contentPadding = PaddingValues(
            vertical = CustomTheme.shape.mediumPadding,
            horizontal = CustomTheme.shape.largePadding
        )
    ) {
        if (text != null) {
            Text(
                text = text,
                style = CustomTheme.typography.buttonText,
                color = if (enabled) {
                    contentColor
                } else {
                    disabledContentColor
                }
            )
        } else if (content != null) {
            content()
        }
    }
}