package com.klyukvin.ingoodhands.ui.screen.main.home.post

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.common.model.UiPost
import com.klyukvin.ingoodhands.common.util.noIndicationClickable
import com.klyukvin.ingoodhands.ui.component.ImagePager
import com.klyukvin.ingoodhands.ui.component.LoadingPostField
import com.klyukvin.ingoodhands.ui.component.LoadingPostMainSection
import com.klyukvin.ingoodhands.ui.component.PostMainSection
import com.klyukvin.ingoodhands.ui.component.custom.CustomCard
import com.klyukvin.ingoodhands.ui.component.custom.CustomIcon
import com.klyukvin.ingoodhands.ui.component.custom.CustomTopBar
import com.klyukvin.ingoodhands.ui.screen.main.home.post.model.UiPostState
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun PostScreen(postId: String) {
    val viewModel = hiltViewModel<PostViewModel>()
    val state by viewModel.state.collectAsState()
    viewModel.loadPost(postId)

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBar()
        Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))

        when (val currentState = state) {
            is UiPostState.Loading -> LoadingContent()
            is UiPostState.Content -> Content(currentState.post)
            else -> Unit
        }
    }
}

@Composable
private fun Content(post: UiPost) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = CustomTheme.shape.horizontalScreenPadding)
            .clip(Shapes.defaultRoundedShape),
        verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)
    ) {
        item {
            ImagePager(imageUris = post.imageUris)
        }
        item {
            MainSection(post)
        }
        item {
            Contacts(post)
        }
    }
}

@Composable
private fun MainSection(post: UiPost) {
    CustomCard(
        modifier = Modifier.fillMaxWidth(),
        useDefaultPaddings = true
    ) {
        PostMainSection(post)
    }
}

@Composable
private fun Contacts(post: UiPost) {
    val context = LocalContext.current

    CustomCard(
        modifier = Modifier.fillMaxWidth(),
        useDefaultPaddings = true
    ) {
        ContactItem(
            icon = painterResource(id = R.drawable.phone),
            text = post.phoneNumber,
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${post.phoneNumber}")
                }
                context.startActivity(intent)
            }
        )
        Spacer(modifier = Modifier.height(CustomTheme.shape.linePadding))
        ContactItem(
            icon = painterResource(id = R.drawable.address),
            text = post.address,
            onClick = {
                val uri = Uri.parse("geo:0,0?q=${post.address}")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                context.startActivity(intent)
            }
        )
    }
}

@Composable
private fun ContactItem(icon: Painter, text: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.noIndicationClickable {
        onClick()
    }) {
        CustomIcon(
            modifier = Modifier.size(CustomTheme.shape.tinySize),
            model = icon,
            tint = CustomTheme.color.accent
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.smallPadding))
        Text(
            text = text,
            style = CustomTheme.typography.primaryBody
        )
    }
}

@Composable
private fun LoadingContent() {
    Column(
        modifier = Modifier.padding(horizontal = CustomTheme.shape.horizontalScreenPadding),
        verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)
    ) {
        LoadingImagePager()
        LoadingMainSection()
        LoadingContacts()
    }
}

@Composable
private fun LoadingImagePager() {
    LoadingPostField(height = CustomTheme.shape.imageHeight)
}

@Composable
private fun LoadingMainSection() {
    CustomCard(useDefaultPaddings = true) {
        LoadingPostMainSection()
    }
}

@Composable
private fun LoadingContacts() {
    CustomCard(
        modifier = Modifier.fillMaxWidth(),
        useDefaultPaddings = true
    ) {
        LoadingContactItem()
        Spacer(modifier = Modifier.height(CustomTheme.shape.linePadding))
        LoadingContactItem()
    }
}

@Composable
private fun LoadingContactItem() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        LoadingPostField(
            width = CustomTheme.shape.tinySize,
            height = CustomTheme.shape.tinySize
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.tinyPadding))
        LoadingPostField(width = 100.dp)
    }
}