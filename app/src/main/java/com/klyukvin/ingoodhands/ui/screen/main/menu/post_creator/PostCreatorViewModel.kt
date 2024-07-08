package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyukvin.domain.usecase.database.post.SavePostUseCase
import com.klyukvin.domain.usecase.post_creator.ArePostCreatorFieldsNotEmptyUseCase
import com.klyukvin.domain.usecase.post_creator.ArePostCreatorFieldsValidUseCase
import com.klyukvin.domain.usecase.post_creator.CheckSelectedImagesCountUseCase
import com.klyukvin.domain.usecase.post_creator.PostCreatorValidationUseCase
import com.klyukvin.ingoodhands.navigation.NavigationState
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.mapper.PostCreatorErrorsMapper
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.mapper.PostCreatorFieldsMapper
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorCategory
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorErrors
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorFields
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostCreatorViewModel @Inject constructor(
    private val savePostUseCase: SavePostUseCase,
    private val checkSelectedImagesCountUseCase: CheckSelectedImagesCountUseCase,
    private val postCreatorValidationUseCase: PostCreatorValidationUseCase,
    private val arePostCreatorFieldsNotEmptyUseCase: ArePostCreatorFieldsNotEmptyUseCase,
    private val arePostCreatorFieldsValidUseCase: ArePostCreatorFieldsValidUseCase,
    private val postCreatorFieldsMapper: PostCreatorFieldsMapper,
    private val postCreatorErrorsMapper: PostCreatorErrorsMapper
) : ViewModel() {

    private val _state = MutableStateFlow<UiPostCreatorState>(UiPostCreatorState.Initial)
    val state = _state.asStateFlow()

    private val _fields = MutableStateFlow(UiPostCreatorFields())
    val fields = _fields.asStateFlow()

    private val _errors = MutableStateFlow(UiPostCreatorErrors())
    val errors = _errors.asStateFlow()

    private val _isSelectedImagesShown = MutableStateFlow(false)
    val isSelectedImagesShown = _isSelectedImagesShown.asStateFlow()

    private val _isCategoryMenuExpanded = MutableStateFlow(false)
    val isCategoryMenuExpanded = _isCategoryMenuExpanded.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled = _isButtonEnabled.asStateFlow()

    init {
        _state.value = UiPostCreatorState.Content
    }

    fun onAddImageButtonClicked(launcher: ManagedActivityResultLauncher<String, Uri?>) {
        if (checkSelectedImagesCountUseCase(_fields.value.imageUris.size)) {
            launcher.launch("image/*")
        }
    }

    fun addImageToList(imageUri: Uri?) {
        if (imageUri != null) {
            updateField {
                copy(imageUris = _fields.value.imageUris + imageUri)
            }
            updateSelectedImagesState()
        }
    }

    fun deleteImageFromList(imageUri: Uri) {
        updateField {
            copy(imageUris = _fields.value.imageUris - imageUri)
        }
        updateSelectedImagesState()
    }

    fun onTitleChanged(title: String) {
        updateField {
            copy(title = title)
        }
    }

    fun onDescriptionChanged(description: String) {
        updateField {
            copy(description = description)
        }
    }

    fun onCategoriesMenuButtonClicked() {
        _isCategoryMenuExpanded.value = !_isCategoryMenuExpanded.value
    }

    fun onCategoryChanged(category: UiPostCreatorCategory) {
        updateField {
            copy(category = category)
        }
        _isCategoryMenuExpanded.value = false
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        updateField {
            copy(phoneNumber = phoneNumber)
        }
    }

    fun onAddressChanged(address: String) {
        updateField {
            copy(address = address)
        }
    }

    fun onCreatePostButtonClicked(navigationState: NavigationState) {
        val domainFields = postCreatorFieldsMapper.mapToDomain(_fields.value)
        val domainErrors = postCreatorValidationUseCase(domainFields)
        val uiErrors = postCreatorErrorsMapper.mapToUi(domainErrors)
        _errors.value = uiErrors

        if (arePostCreatorFieldsValidUseCase(domainErrors)) {
            viewModelScope.launch {
                _state.value = UiPostCreatorState.Loading
                val initialPostParameters = postCreatorFieldsMapper.mapToInitialPostParameters(_fields.value)
                savePostUseCase(initialPostParameters)
                navigationState.navigate(Screen.Feed.route)
            }
        }
    }

    private fun updateSelectedImagesState() {
        _isSelectedImagesShown.value = _fields.value.imageUris.isNotEmpty()
    }

    private fun updateField(updater: UiPostCreatorFields.() -> UiPostCreatorFields) {
        _fields.value = _fields.value.updater()
        checkButtonState()
    }

    private fun checkButtonState() {
        val domainFields = postCreatorFieldsMapper.mapToDomain(_fields.value)
        _isButtonEnabled.value = arePostCreatorFieldsNotEmptyUseCase(domainFields)
    }
}