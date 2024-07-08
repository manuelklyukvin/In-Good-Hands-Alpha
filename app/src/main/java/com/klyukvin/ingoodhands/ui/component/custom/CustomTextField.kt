package com.klyukvin.ingoodhands.ui.component.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    error: String? = null,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label,
                style = CustomTheme.typography.secondaryBody
            )
        }
        TextField(
            modifier = contentModifier,
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                if (placeholder != null) {
                    Text(
                        text = placeholder,
                        style = CustomTheme.typography.textFieldHint
                    )
                }
            },
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(
                cursorColor = CustomTheme.color.accent,
                focusedContainerColor = CustomTheme.color.textFieldBackground,
                unfocusedContainerColor = CustomTheme.color.textFieldBackground,
                disabledContainerColor = CustomTheme.color.textFieldBackground,
                errorContainerColor = CustomTheme.color.textFieldBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            shape = Shapes.defaultRoundedShape,
            textStyle = CustomTheme.typography.textFieldText
        )
        if (error != null) {
            Text(
                text = error,
                style = CustomTheme.typography.warning
            )
        }
    }
}