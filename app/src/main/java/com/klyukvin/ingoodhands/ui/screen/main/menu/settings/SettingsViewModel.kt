package com.klyukvin.ingoodhands.ui.screen.main.menu.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyukvin.domain.usecase.database.user.GetCurrentUserUseCase
import com.klyukvin.domain.usecase.database.user.LogoutUserUseCase
import com.klyukvin.ingoodhands.common.mapper.UserMapper
import com.klyukvin.ingoodhands.common.model.UiUser
import com.klyukvin.ingoodhands.navigation.NavigationState
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.ui.screen.main.menu.settings.model.UiSettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val userMapper: UserMapper
) : ViewModel() {

    private val _state = MutableStateFlow<UiSettingsState>(UiSettingsState.Initial)
    val state = _state.asStateFlow()

    private val _currentUser = MutableStateFlow<UiUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _state.value = UiSettingsState.Loading
            _currentUser.value = getCurrentUserUseCase()?.let {
                userMapper.mapToUi(it)
            }
            _state.value = UiSettingsState.Content
        }
    }

    fun onModerationButtonClicked(navigationState: NavigationState) {

    }

    fun onPostCreatorButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.PostCreator.route)
    }

    fun onLogoutButtonClicked(navigationState: NavigationState) {
        viewModelScope.launch {
            logoutUserUseCase()
            navigationState.navigate(route = Screen.Login.route)
        }
    }

    fun onLoginButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(route = Screen.Login.route)
    }
}