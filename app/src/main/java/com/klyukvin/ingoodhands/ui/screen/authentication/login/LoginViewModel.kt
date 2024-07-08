package com.klyukvin.ingoodhands.ui.screen.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyukvin.domain.common.util.Result
import com.klyukvin.domain.usecase.authentication.login.AreLoginFieldsNotEmptyUseCase
import com.klyukvin.domain.usecase.authentication.login.AreLoginFieldsValidUseCase
import com.klyukvin.domain.usecase.authentication.login.LoginValidationUseCase
import com.klyukvin.domain.usecase.database.user.GetUserErrorUseCase
import com.klyukvin.domain.usecase.database.user.LoginUserUseCase
import com.klyukvin.domain.usecase.database.user.LogoutUserUseCase
import com.klyukvin.ingoodhands.navigation.NavigationState
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.ui.screen.authentication.login.mapper.LoginErrorsMapper
import com.klyukvin.ingoodhands.ui.screen.authentication.login.mapper.LoginFieldsMapper
import com.klyukvin.ingoodhands.ui.screen.authentication.login.model.UiLoginErrors
import com.klyukvin.ingoodhands.ui.screen.authentication.login.model.UiLoginFields
import com.klyukvin.ingoodhands.ui.screen.authentication.login.model.UiLoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginValidationUseCase: LoginValidationUseCase,
    private val areLoginFieldsValidUseCase: AreLoginFieldsValidUseCase,
    private val areLoginFieldsNotEmptyUseCase: AreLoginFieldsNotEmptyUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val getUserErrorUseCase: GetUserErrorUseCase,
    private val loginFieldsMapper: LoginFieldsMapper,
    private val loginErrorsMapper: LoginErrorsMapper
) : ViewModel() {

    private val _state = MutableStateFlow<UiLoginState>(UiLoginState.Initial)
    val state = _state.asStateFlow()

    private val _fields = MutableStateFlow(UiLoginFields())
    val fields = _fields.asStateFlow()

    private val _errors = MutableStateFlow(UiLoginErrors())
    val errors = _errors.asStateFlow()

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError = _loginError.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled = _isButtonEnabled.asStateFlow()

    init {
        _state.value = UiLoginState.Content
    }

    fun onEmailChanged(email: String) {
        updateField {
            copy(email = email)
        }
    }

    fun onPasswordChanged(password: String) {
        updateField {
            copy(password = password)
        }
    }

    fun onRegistrationButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.Registration.route)
    }

    fun onForgotPasswordButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(
            route = Screen.Main.route,
            clearBackStack = true
        )
    }

    fun onLoginAsGuestButtonClicked(navigationState: NavigationState) {
        viewModelScope.launch {
            logoutUserUseCase()
            navigationState.navigate(
                route = Screen.Main.route,
                clearBackStack = true
            )
        }
    }

    fun onLoginButtonClicked(navigationState: NavigationState) {
        val domainFields = loginFieldsMapper.mapToDomain(_fields.value)
        val domainErrors = loginValidationUseCase(domainFields)
        _errors.value = loginErrorsMapper.mapToUi(domainErrors)

        if (areLoginFieldsValidUseCase(domainErrors)) {
            viewModelScope.launch {
                _state.value = UiLoginState.Loading
                val loginUserData = loginFieldsMapper.mapToDomainUserData(_fields.value)
                when (val loginResult = loginUserUseCase(loginUserData)) {
                    is Result.Error -> {
                        _loginError.value = getUserErrorUseCase(loginResult.error)
                        _state.value = UiLoginState.Content
                    }
                    is Result.Success -> navigationState.navigate(
                        route = Screen.Main.route,
                        clearBackStack = true
                    )
                }
            }
        }
    }

    private fun updateField(updater: UiLoginFields.() -> UiLoginFields) {
        _fields.value = _fields.value.updater()
        checkButtonState()
    }

    private fun checkButtonState() {
        val domainFields = loginFieldsMapper.mapToDomain(_fields.value)
        _isButtonEnabled.value = areLoginFieldsNotEmptyUseCase(domainFields)
    }
}