package com.klyukvin.ingoodhands.ui.screen.main.menu.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyukvin.domain.usecase.database.user.GetCurrentUserUseCase
import com.klyukvin.ingoodhands.common.mapper.UserMapper
import com.klyukvin.ingoodhands.common.model.UiUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val userMapper: UserMapper
) : ViewModel() {

    private val _state = MutableStateFlow<AccountState>(AccountState.Initial)
    val state = _state.asStateFlow()

    private val _currentUser = MutableStateFlow<UiUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _state.value = AccountState.Loading
            _currentUser.value = getCurrentUserUseCase()?.let {
                userMapper.mapToUi(it)
            }
            _state.value = AccountState.Content
        }
    }
}