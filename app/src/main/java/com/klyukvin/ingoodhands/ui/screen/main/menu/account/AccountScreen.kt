package com.klyukvin.ingoodhands.ui.screen.main.menu.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.ui.component.custom.CustomCard
import com.klyukvin.ingoodhands.ui.component.custom.CustomIcon
import com.klyukvin.ingoodhands.ui.component.custom.CustomTopBar
import com.klyukvin.ingoodhands.ui.theme.CustomTheme

@Composable
fun AccountScreen() {
    val viewModel = hiltViewModel<AccountViewModel>()
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBar()
        Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))

        when (state) {
            is AccountState.Loading -> LoadingContent()
            is AccountState.Content -> Content(viewModel)
            else -> Unit
        }
    }
}

@Composable
private fun Content(viewModel: AccountViewModel) {
    val user by viewModel.currentUser.collectAsState()

    CustomCard(
        modifier = Modifier.fillMaxWidth().padding(horizontal = CustomTheme.shape.horizontalScreenPadding),
        useDefaultPaddings = true
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomIcon(
                modifier = Modifier.size(CustomTheme.shape.largeSize),
                model = painterResource(id = R.drawable.account),
                tint = CustomTheme.color.accent
            )
            Spacer(modifier = Modifier.width(CustomTheme.shape.largePadding))
            Text(
                text = user?.username ?: stringResource(id = R.string.settings_guest_account),
                style = CustomTheme.typography.primaryHeading
            )
            Text(
                text = user?.email ?: "",
                style = CustomTheme.typography.secondaryBody
            )
        }
    }
}

@Composable
private fun LoadingContent() {

}