package com.klyukvin.ingoodhands.ui.screen.main.home.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.common.model.UiPost
import com.klyukvin.ingoodhands.common.util.noIndicationClickable
import com.klyukvin.ingoodhands.ui.component.ImagePager
import com.klyukvin.ingoodhands.ui.component.LoadingPostField
import com.klyukvin.ingoodhands.ui.component.LoadingPostMainSection
import com.klyukvin.ingoodhands.ui.component.PostMainSection
import com.klyukvin.ingoodhands.ui.component.custom.CustomButton
import com.klyukvin.ingoodhands.ui.component.custom.CustomCard
import com.klyukvin.ingoodhands.ui.component.custom.CustomIcon
import com.klyukvin.ingoodhands.ui.component.custom.CustomTextField
import com.klyukvin.ingoodhands.ui.screen.main.home.feed.model.UiFeedState
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.LocalMainNavigationState
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun FeedScreen() {
    val viewModel = hiltViewModel<FeedViewModel>()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = CustomTheme.shape.horizontalScreenPadding)
    ) {
        SearchBar(viewModel)
        Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))

        when (val currentState = state) {
            is UiFeedState.Loading -> LoadingContent()
            is UiFeedState.Content -> Content(viewModel, currentState.postList)
            else -> Unit
        }
    }
}

@Composable
private fun SearchBar(viewModel: FeedViewModel) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val navigationState = LocalMainNavigationState.current

    Row(
        modifier = Modifier.height(CustomTheme.shape.smallSize),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTextField(
            modifier = Modifier.weight(1f),
            value = searchQuery,
            onValueChange = { searchQuery ->
                viewModel.onSearchQueryChanged(searchQuery)
            },
            placeholder = stringResource(id = R.string.feed_search)
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.smallPadding))
        IconButton(
            onClick = {
                viewModel.onSearchButtonClicked(navigationState)
            }
        ) {
            CustomIcon(
                modifier = Modifier.size(CustomTheme.shape.tinySize),
                model = painterResource(id = R.drawable.search),
                tint = CustomTheme.color.accent
            )
        }
    }
}

@Composable
private fun Content(viewModel: FeedViewModel, postList: List<UiPost>) {
    val pageButtonsState by viewModel.pageButtonsState.collectAsState()

    LazyColumn(
        modifier = Modifier.clip(Shapes.defaultRoundedShape),
        verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)
    ) {
        items(postList) { post ->
            PostCard(viewModel, post)
        }
        if (pageButtonsState.isPageButtonsShown) {
            item {
                PageButtons(viewModel)
            }
        }
    }
}

@Composable
private fun PostCard(viewModel: FeedViewModel, post: UiPost) {
    val navigationState = LocalMainNavigationState.current

    CustomCard(
        modifier = Modifier.noIndicationClickable {
            viewModel.onPostClicked(navigationState, post.id)
        }
    ) {
        ImagePager(
            imageUris = post.imageUris,
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier.padding(CustomTheme.shape.largePadding)) {
            PostMainSection(
                post = post,
                isDescriptionHidden = true
            )
        }
    }
}

@Composable
private fun PageButtons(viewModel: FeedViewModel) {
    val pageButtonsState by viewModel.pageButtonsState.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)
    ) {
        if (pageButtonsState.isPreviousPageButtonShown) {
            PreviousPageButton(viewModel)
        }
        if (pageButtonsState.isNextPageButtonShown) {
            NextPageButton(viewModel)
        }
    }
}

@Composable
private fun RowScope.PreviousPageButton(viewModel: FeedViewModel) {
    CustomButton(
        modifier = Modifier.weight(1f),
        containerColor = CustomTheme.color.secondaryButtonContainer,
        onClick = {
            viewModel.loadPreviousPage()
        }
    ) {
        CustomIcon(
            modifier = Modifier.size(CustomTheme.shape.tinySize),
            model = painterResource(id = R.drawable.arrow_back),
            tint = CustomTheme.color.accent
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.linePadding))
        Text(
            text = stringResource(id = R.string.feed_previous_page),
            style = CustomTheme.typography.buttonText,
            color = CustomTheme.color.accent
        )
    }
}

@Composable
private fun RowScope.NextPageButton(viewModel: FeedViewModel) {
    CustomButton(
        modifier = Modifier.weight(1f),
        containerColor = CustomTheme.color.secondaryButtonContainer,
        onClick = {
            viewModel.loadNextPage()
        }
    ) {
        Text(
            text = stringResource(id = R.string.feed_next_page),
            style = CustomTheme.typography.buttonText,
            color = CustomTheme.color.accent
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.linePadding))
        CustomIcon(
            modifier = Modifier.size(CustomTheme.shape.tinySize),
            model = painterResource(id = R.drawable.arrow_forward),
            tint = CustomTheme.color.accent
        )
    }
}

@Composable
private fun LoadingContent() {
    Column(verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)) {
        repeat(2) {
            CustomCard {
                LoadingPostField(height = CustomTheme.shape.imageHeight)
                Column(modifier = Modifier.padding(CustomTheme.shape.largePadding)) {
                    LoadingPostMainSection()
                }
            }
        }
    }
}