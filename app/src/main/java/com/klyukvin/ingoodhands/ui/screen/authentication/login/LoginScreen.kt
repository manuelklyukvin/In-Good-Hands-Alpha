package com.klyukvin.ingoodhands.ui.screen.authentication.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.common.util.noIndicationClickable
import com.klyukvin.ingoodhands.ui.component.ScreenBlocking
import com.klyukvin.ingoodhands.ui.component.custom.CustomButton
import com.klyukvin.ingoodhands.ui.component.custom.CustomTextField
import com.klyukvin.ingoodhands.ui.component.custom.LoadingCircle
import com.klyukvin.ingoodhands.ui.component.custom.PasswordField
import com.klyukvin.ingoodhands.ui.screen.authentication.AuthenticationScreen
import com.klyukvin.ingoodhands.ui.screen.authentication.login.model.UiLoginState
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.LocalAppNavigationState

@Composable
fun LoginScreen() {
    val viewModel = hiltViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        AuthenticationScreen(
            title = stringResource(id = R.string.login_title),
            subtitle = stringResource(id = R.string.login_subtitle),
            formFields = {
                FormFields(viewModel)
            },
            formButtons = {
                FormButtons(viewModel)
            }
        )
        if (state is UiLoginState.Loading) {
            ScreenBlocking()
        }
    }
}

@Composable
private fun FormFields(viewModel: LoginViewModel) {
    val fields by viewModel.fields.collectAsState()
    val errors by viewModel.errors.collectAsState()

    CustomTextField(
        value = fields.email,
        onValueChange = { email ->
            viewModel.onEmailChanged(email)
        },
        label = stringResource(id = R.string.authentication_email),
        placeholder = stringResource(id = R.string.authentication_email_placeholder),
        error = errors.emailError,
        keyboardType = KeyboardType.Email
    )
    PasswordField(
        value = fields.password,
        onValueChange = { password ->
            viewModel.onPasswordChanged(password)
        },
        label = stringResource(id = R.string.authentication_password),
        placeholder = stringResource(id = R.string.authentication_password_placeholder),
        error = errors.passwordError
    )
}

@Composable
private fun FormButtons(viewModel: LoginViewModel) {
    val state by viewModel.state.collectAsState()
    val loginError by viewModel.loginError.collectAsState()
    val isButtonEnabled by viewModel.isButtonEnabled.collectAsState()
    val navigationState = LocalAppNavigationState.current

    Row {
        Text(
            text = stringResource(id = R.string.login_registration_title),
            style = CustomTheme.typography.secondaryBody
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.tinyPadding))
        Text(
            modifier = Modifier.noIndicationClickable {
                viewModel.onRegistrationButtonClicked(navigationState)
            },
            text = stringResource(id = R.string.login_registration_button),
            style = CustomTheme.typography.primaryBody,
            color = CustomTheme.color.accent
        )
    }
    Text(
        modifier = Modifier.noIndicationClickable {
            viewModel.onLoginAsGuestButtonClicked(navigationState)
        },
        text = stringResource(id = R.string.login_as_guest_button),
        style = CustomTheme.typography.secondaryBody,
        color = CustomTheme.color.accent
    )
    Spacer(modifier = Modifier.height(CustomTheme.shape.linePadding))
    CustomButton(
        text = if (state !is UiLoginState.Loading) {
            stringResource(id = R.string.login_button)
        } else {
            null
        },
        content = {
            if (state is UiLoginState.Loading) {
                LoadingCircle()
            }
        },
        onClick = {
            viewModel.onLoginButtonClicked(navigationState)
        },
        enabled = isButtonEnabled
    )
    if (loginError != null) {
        Spacer(modifier = Modifier.height(CustomTheme.shape.linePadding))
        Text(
            text = loginError!!,
            style = CustomTheme.typography.warning
        )
    }
}