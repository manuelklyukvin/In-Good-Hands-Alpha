package com.klyukvin.ingoodhands.ui.screen.main.menu.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.common.util.noIndicationClickable
import com.klyukvin.ingoodhands.ui.component.LoadingPostField
import com.klyukvin.ingoodhands.ui.component.custom.CustomCard
import com.klyukvin.ingoodhands.ui.component.custom.CustomIcon
import com.klyukvin.ingoodhands.ui.screen.main.menu.settings.model.UiSettingsState
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.LocalMainNavigationState
import com.klyukvin.ingoodhands.ui.theme.resource.Colors
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun SettingsScreen() {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar()
        Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))

        when (state) {
            is UiSettingsState.Loading -> LoadingContent()
            is UiSettingsState.Content -> Content(viewModel)
            else -> Unit
        }
    }
}

@Composable
private fun TitleBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(CustomTheme.shape.smallSize),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomIcon(
                modifier = Modifier.size(CustomTheme.shape.tinySize),
                model = painterResource(id = R.drawable.logo),
                tint = CustomTheme.color.accent
            )
            Spacer(modifier = Modifier.width(CustomTheme.shape.mediumPadding))
            Text(
                text = stringResource(id = R.string.app_name),
                style = CustomTheme.typography.primaryHeading,
                fontSize = 20.sp
            )
        }
        HorizontalDivider(color = Colors.gray)
    }
}

@Composable
private fun Content(viewModel: SettingsViewModel) {
    val user by viewModel.currentUser.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = CustomTheme.shape.horizontalScreenPadding)
            .clip(Shapes.defaultRoundedShape),
        verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)
    ) {
        item {
            User(viewModel)
        }
        if (user != null) {
            item {
                PostButtons(viewModel)
            }
        }
    }
}

@Composable
private fun User(viewModel: SettingsViewModel) {
    val user by viewModel.currentUser.collectAsState()
    val navigationState = LocalMainNavigationState.current

    CustomCard(
        modifier = Modifier.fillMaxWidth().noIndicationClickable {
            if (user != null) {
                navigationState.navigate("account")
            }
        },
        useDefaultPaddings = true
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CustomIcon(
                modifier = Modifier.size(CustomTheme.shape.smallSize),
                model = painterResource(id = R.drawable.account),
                tint = CustomTheme.color.accent
            )
            Spacer(modifier = Modifier.width(CustomTheme.shape.largePadding))
            Text(
                text = user?.username ?: stringResource(id = R.string.settings_guest_account),
                style = CustomTheme.typography.primaryHeading
            )
        }
    }
}

@Composable
private fun PostButtons(viewModel: SettingsViewModel) {
    val navigationState = LocalMainNavigationState.current

    CustomCard {
        SettingsButton(
            text = stringResource(id = R.string.settings_post_creator),
            onClick = {
                viewModel.onPostCreatorButtonClicked(navigationState)
            }
        )
    }
}

@Composable
private fun SettingsButton(text: String, onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = CustomTheme.shape.mediumPadding,
                horizontal = CustomTheme.shape.largePadding
            )
            .noIndicationClickable {
                onClick()
            },
        text = text,
        style = CustomTheme.typography.primaryBody
    )
}

@Composable
private fun LoadingContent() {
    Column(
        modifier = Modifier.padding(horizontal = CustomTheme.shape.horizontalScreenPadding),
        verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)
    ) {
        LoadingPostField(height = 70.dp)
        LoadingPostField(height = 40.dp)
    }
}