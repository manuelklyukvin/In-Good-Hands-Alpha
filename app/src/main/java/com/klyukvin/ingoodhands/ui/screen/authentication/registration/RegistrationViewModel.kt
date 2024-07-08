package com.klyukvin.ingoodhands.ui.screen.authentication.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyukvin.domain.usecase.authentication.registration.AreRegistrationFieldsNotEmptyUseCase
import com.klyukvin.domain.usecase.authentication.registration.AreRegistrationFieldsValidUseCase
import com.klyukvin.domain.usecase.authentication.registration.RegistrationValidationUseCase
import com.klyukvin.domain.usecase.database.user.RegisterUserUseCase
import com.klyukvin.ingoodhands.navigation.NavigationState
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.mapper.RegistrationErrorsMapper
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.mapper.RegistrationFieldsMapper
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.model.UiRegistrationErrors
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.model.UiRegistrationFields
import com.klyukvin.ingoodhands.ui.screen.authentication.registration.model.UiRegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationValidationUseCase: RegistrationValidationUseCase,
    private val areRegistrationFieldsValidUseCase: AreRegistrationFieldsValidUseCase,
    private val areRegistrationFieldsNotEmptyUseCase: AreRegistrationFieldsNotEmptyUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val registrationFieldsMapper: RegistrationFieldsMapper,
    private val registrationErrorsMapper: RegistrationErrorsMapper
) : ViewModel() {

    private val _state = MutableStateFlow<UiRegistrationState>(UiRegistrationState.Initial)
    val state = _state.asStateFlow()

    private val _fields = MutableStateFlow(UiRegistrationFields())
    val fields = _fields.asStateFlow()

    private val _errors = MutableStateFlow(UiRegistrationErrors())
    val errors = _errors.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled = _isButtonEnabled.asStateFlow()

    init {
        _state.value = UiRegistrationState.Content
    }

    fun onUsernameChanged(username: String) {
        updateField {
            copy(username = username)
        }
    }

    fun onFirstnameChanged(firstname: String) {
        updateField {
            copy(firstname = firstname)
        }
    }

    fun onLastnameChanged(lastname: String) {
        updateField {
            copy(lastname = lastname)
        }
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

    fun onConfirmPasswordChanged(confirmPassword: String) {
        updateField {
            copy(confirmPassword = confirmPassword)
        }
    }

    fun onLoginButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.Login.route)
    }

    fun onRegistrationButtonClicked(navigationState: NavigationState) {
        val domainFields = registrationFieldsMapper.mapToDomain(_fields.value)
        val domainErrors = registrationValidationUseCase(domainFields)
        _errors.value = registrationErrorsMapper.mapToUi(domainErrors)

        if (areRegistrationFieldsValidUseCase(domainErrors)) {
            viewModelScope.launch {
                _state.value = UiRegistrationState.Loading
                val registrationUserData = registrationFieldsMapper.mapToDomainUserData(_fields.value)
                registerUserUseCase(registrationUserData)
                navigationState.navigate(
                    route = Screen.Main.route,
                    clearBackStack = true
                )
            }
        }
    }

    private fun updateField(updater: UiRegistrationFields.() -> UiRegistrationFields) {
        _fields.value = _fields.value.updater()
        checkButtonState()
    }

    private fun checkButtonState() {
        val domainFields = registrationFieldsMapper.mapToDomain(_fields.value)
        _isButtonEnabled.value = areRegistrationFieldsNotEmptyUseCase(domainFields)
    }
}