package com.klyukvin.ingoodhands.ui.screen.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.klyukvin.ingoodhands.ui.component.custom.CustomCard
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun AuthenticationScreen(
    title: String,
    subtitle: String,
    formFields: @Composable () -> Unit,
    formButtons: @Composable () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = CustomTheme.shape.horizontalScreenPadding)
            .clip(Shapes.defaultRoundedShape)
    ) {
        item {
            Column(
                modifier = Modifier.padding(vertical = CustomTheme.shape.verticalScreenPadding)
            ) {
                Title(title, subtitle)
                Spacer(modifier = Modifier.height(CustomTheme.shape.largePadding))
                Form(formFields, formButtons)
            }
        }
    }
}

@Composable
private fun Title(title: String, subtitle: String) {
    Text(
        text = title,
        style = CustomTheme.typography.primaryHeading
    )
    Text(
        text = subtitle,
        style = CustomTheme.typography.primaryBody
    )
}

@Composable
private fun Form(
    formFields: @Composable () -> Unit,
    formButtons: @Composable () -> Unit
) {
    CustomCard(
        useDefaultPaddings = true,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)) {
            formFields()
        }
        Spacer(modifier = Modifier.height(CustomTheme.shape.largePadding))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            formButtons()
        }
    }
}