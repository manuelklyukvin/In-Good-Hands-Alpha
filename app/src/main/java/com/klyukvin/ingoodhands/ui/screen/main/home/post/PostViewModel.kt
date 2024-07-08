package com.klyukvin.ingoodhands.ui.screen.main.home.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyukvin.domain.usecase.database.post.LoadPostByIdUseCase
import com.klyukvin.ingoodhands.common.mapper.PostMapper
import com.klyukvin.ingoodhands.ui.screen.main.home.post.model.UiPostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val loadPostByIdUseCase: LoadPostByIdUseCase,
    private val postMapper: PostMapper
) : ViewModel() {

    private val _state = MutableStateFlow<UiPostState>(UiPostState.Initial)
    val state = _state.asStateFlow()

    fun loadPost(postId: String) {
        if (state.value !is UiPostState.Content) {
            viewModelScope.launch {
                _state.value = UiPostState.Loading
                val domainPost = loadPostByIdUseCase(postId)
                val uiPost = postMapper.mapToUi(domainPost)
                _state.value = UiPostState.Content(uiPost)
            }
        }
    }
}