package com.klyukvin.ingoodhands.ui.component.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    error: String? = null
) {
    var isPasswordHidden by remember {
        mutableStateOf(true)
    }

    val buttonTint = if (isPasswordHidden) {
        CustomTheme.color.secondaryText
    } else {
        CustomTheme.color.primaryText
    }

    val visualTransformation = if (isPasswordHidden) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
            textStyle = CustomTheme.typography.textFieldText,
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordHidden = !isPasswordHidden
                    }
                ) {
                    CustomIcon(
                        model = painterResource(id = R.drawable.eye),
                        tint = buttonTint
                    )
                }
            },
            visualTransformation = visualTransformation
        )
        if (error != null) {
            Text(
                text = error,
                style = CustomTheme.typography.warning
            )
        }
    }
}