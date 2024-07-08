package com.klyukvin.ingoodhands.ui.screen.introduction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.ui.component.custom.CustomButton
import com.klyukvin.ingoodhands.ui.component.custom.CustomIcon
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.LocalAppNavigationState

@Composable
fun IntroductionScreen() {
    val viewModel = hiltViewModel<IntroductionViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = CustomTheme.shape.largeSize,
                horizontal = CustomTheme.shape.horizontalScreenPadding
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        StartButton(viewModel)
    }
}

@Composable
private fun Title() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.introduction_title),
            style = CustomTheme.typography.primaryHeading,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.introduction_subtitle),
            style = CustomTheme.typography.primaryBody,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(CustomTheme.shape.largeSize))
        CustomIcon(
            modifier = Modifier.size(CustomTheme.shape.largeSize),
            model = painterResource(id = R.drawable.heart),
            tint = CustomTheme.color.accent
        )
    }
}

@Composable
private fun StartButton(viewModel: IntroductionViewModel) {
    val navigationState = LocalAppNavigationState.current

    CustomButton(
        text = stringResource(id = R.string.introduction_button),
        onClick = {
            viewModel.onStartButtonClicked(navigationState)
        }
    )
}