package com.klyukvin.ingoodhands.ui.screen.main.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.klyukvin.data.usecase.database.model.DomainSnapshotImpl
import com.klyukvin.data.usecase.database.post.util.PostReferenceProviderImpl
import com.klyukvin.domain.usecase.database.post.GetPageCountUseCase
import com.klyukvin.domain.usecase.database.post.LoadPostsOnPageUseCase
import com.klyukvin.domain.usecase.database.post.mapper.DomainPostMapper
import com.klyukvin.ingoodhands.common.mapper.PostMapper
import com.klyukvin.ingoodhands.navigation.NavigationState
import com.klyukvin.ingoodhands.navigation.Screen
import com.klyukvin.ingoodhands.ui.screen.main.home.feed.model.UiFeedPageButtonsState
import com.klyukvin.ingoodhands.ui.screen.main.home.feed.model.UiFeedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val loadPostsOnPageUseCase: LoadPostsOnPageUseCase,
    private val getPageCountUseCase: GetPageCountUseCase,
    private val postMapper: PostMapper
) : ViewModel() {

    private val _state = MutableStateFlow<UiFeedState>(UiFeedState.Initial)
    val state = _state.asStateFlow()

    private var currentPage = 1
    private var pageCount = 1

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _pageButtonsState = MutableStateFlow(UiFeedPageButtonsState())
    val pageButtonsState = _pageButtonsState.asStateFlow()

    init {
        loadScreen()
    }

    private fun loadScreen() {
        loadPostsOnPage()
        getPageCount()
    }

    private fun loadPostsOnPage() {
        viewModelScope.launch {
            _state.value = UiFeedState.Loading
            val postList = loadPostsOnPageUseCase(currentPage)
            _state.value = UiFeedState.Content(
                postList.map { domainPost ->
                    postMapper.mapToUi(domainPost)
                }
            )
            updateButtonsState()
        }
    }

    private fun getPageCount() {
        viewModelScope.launch {
            pageCount = getPageCountUseCase()
            updateButtonsState()
        }
    }

    private fun updateButtonsState() {
        val isPreviousPageButtonShown = currentPage > 1
        val isNextPageButtonShown = currentPage < pageCount
        val isPageButtonsShown = isPreviousPageButtonShown || isNextPageButtonShown

        _pageButtonsState.value = _pageButtonsState.value.copy(
            isPageButtonsShown = isPageButtonsShown,
            isPreviousPageButtonShown = isPreviousPageButtonShown,
            isNextPageButtonShown = isNextPageButtonShown
        )
    }

    fun onSearchQueryChanged(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    fun onSearchButtonClicked(navigationState: NavigationState) {
        if (_searchQuery.value.isEmpty()) {
            return
        }
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("post")

        ref.orderByChild("title").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.filterNotNull().map { dataSnapshot ->
                    val post = DomainPostMapper(PostReferenceProviderImpl()).mapToPost(DomainSnapshotImpl(dataSnapshot))
                    if (post.title.contains(_searchQuery.value, ignoreCase = true)) {
                        navigationState.navigate(Screen.Post.getRouteWithArgs(post.id))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun onPostClicked(navigationState: NavigationState, postId: String) {
        navigationState.navigate(Screen.Post.getRouteWithArgs(postId))
    }

    fun loadNextPage() {
        currentPage += 1
        loadPostsOnPage()
    }

    fun loadPreviousPage() {
        currentPage -= 1
        loadPostsOnPage()
    }
}