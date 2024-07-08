package com.klyukvin.ingoodhands.ui.screen.authentication.registration

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
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.model.UiRegistrationState
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.LocalAppNavigationState

@Composable
fun RegistrationScreen() {
    val viewModel = hiltViewModel<RegistrationViewModel>()
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        AuthenticationScreen(
            title = stringResource(id = R.string.registration_title),
            subtitle = stringResource(id = R.string.registration_subtitle),
            formFields = {
                FormFields(viewModel)
            },
            formButtons = {
                FormButtons(viewModel)
            }
        )
        if (state is UiRegistrationState.Loading) {
            ScreenBlocking()
        }
    }
}

@Composable
private fun FormFields(viewModel: RegistrationViewModel) {
    val fields by viewModel.fields.collectAsState()
    val errors by viewModel.errors.collectAsState()

    CustomTextField(
        value = fields.username,
        onValueChange = { username ->
            viewModel.onUsernameChanged(username)
        },
        label = stringResource(id = R.string.authentication_username),
        placeholder = stringResource(id = R.string.authentication_username_placeholder),
        error = errors.usernameError
    )
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
    PasswordField(
        value = fields.confirmPassword,
        onValueChange = { confirmPassword ->
            viewModel.onConfirmPasswordChanged(confirmPassword)
        },
        label = stringResource(id = R.string.authentication_confirm_password),
        placeholder = stringResource(id = R.string.authentication_confirm_password_placeholder),
        error = errors.confirmPasswordError
    )
}

@Composable
private fun FormButtons(viewModel: RegistrationViewModel) {
    val state by viewModel.state.collectAsState()
    val isButtonEnabled by viewModel.isButtonEnabled.collectAsState()
    val navigationState = LocalAppNavigationState.current

    Row {
        Text(
            text = stringResource(id = R.string.registration_login_title),
            style = CustomTheme.typography.secondaryBody
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.tinyPadding))
        Text(
            modifier = Modifier.noIndicationClickable {
                viewModel.onLoginButtonClicked(navigationState)
            },
            text = stringResource(id = R.string.registration_login_button),
            style = CustomTheme.typography.primaryBody,
            color = CustomTheme.color.accent
        )
    }
    Spacer(modifier = Modifier.height(CustomTheme.shape.linePadding))
    CustomButton(
        text = if (state !is UiRegistrationState.Loading) {
            stringResource(id = R.string.registration_button)
        } else {
            null
        },
        content = {
            if (state is UiRegistrationState.Loading) {
                LoadingCircle()
            }
        },
        onClick = {
            viewModel.onRegistrationButtonClicked(navigationState)
        },
        enabled = isButtonEnabled
    )
}