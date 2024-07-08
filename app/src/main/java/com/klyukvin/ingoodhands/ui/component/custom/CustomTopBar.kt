package com.klyukvin.ingoodhands.ui.component.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.LocalMainNavigationState
import com.klyukvin.ingoodhands.ui.theme.resource.Colors

@Composable
fun CustomTopBar() {
    val navigationState = LocalMainNavigationState.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(CustomTheme.shape.smallSize)
    ) {
        IconButton(
            modifier = Modifier
                .weight(1f)
                .padding(start = CustomTheme.shape.horizontalScreenPadding),
            onClick = {
                navigationState.navController.popBackStack()
            }
        ) {
            CustomIcon(
                modifier = Modifier.size(CustomTheme.shape.tinySize),
                model = painterResource(id = R.drawable.arrow_back),
                tint = CustomTheme.color.accent
            )
        }
        HorizontalDivider(color = Colors.gray)
    }
}