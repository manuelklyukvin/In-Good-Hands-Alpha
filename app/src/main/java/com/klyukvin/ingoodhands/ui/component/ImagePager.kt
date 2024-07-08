package com.klyukvin.ingoodhands.ui.component

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.ui.component.custom.CustomImage
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.resource.Colors
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(imageUris: List<Uri>, contentScale: ContentScale = ContentScale.Fit) {
    val pagerState = rememberPagerState(
        pageCount = {
            imageUris.size
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(CustomTheme.shape.imageHeight)
            .clip(Shapes.defaultRoundedShape)
            .background(Colors.gray),
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(pagerState) { page ->
            CustomImage(
                modifier = Modifier.fillMaxSize(),
                model = imageUris[page],
                contentScale = contentScale
            )
        }
        if (pagerState.pageCount > 1) {
            ImagePagerCounter(pagerState)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImagePagerCounter(pagerState: PagerState) {
    val currentPageNumber = pagerState.currentPage + 1

    Row(
        modifier = Modifier
            .padding(bottom = CustomTheme.shape.largePadding)
            .clip(Shapes.defaultRoundedShape)
            .background(Colors.transparentBlack)
            .padding(horizontal = CustomTheme.shape.smallPadding),
        horizontalArrangement = Arrangement.spacedBy(CustomTheme.shape.tinyPadding)
    ) {
        Text(
            text = currentPageNumber.toString(),
            style = CustomTheme.typography.primaryBody,
            color = CustomTheme.color.accent
        )
        Text(
            text = stringResource(id = R.string.main_image_counter),
            style = CustomTheme.typography.primaryBody,
            color = Colors.white
        )
        Text(
            text = pagerState.pageCount.toString(),
            style = CustomTheme.typography.primaryBody,
            color = Colors.white
        )
    }
}