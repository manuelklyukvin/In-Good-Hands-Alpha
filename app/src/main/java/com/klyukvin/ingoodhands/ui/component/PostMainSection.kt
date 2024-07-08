package com.klyukvin.ingoodhands.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.common.model.UiPost
import com.klyukvin.ingoodhands.ui.component.custom.CustomIcon
import com.klyukvin.ingoodhands.ui.theme.CustomTheme

@Composable
fun PostMainSection(
    post: UiPost,
    isDescriptionHidden: Boolean = false
) {
    Column {
        Title(post.title)
        User(post.user.username)
        Category(post.category)
        Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))
        Description(post.description, isDescriptionHidden)
        Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))
        Date(post.date)
    }
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        style = CustomTheme.typography.primaryHeading
    )
}

@Composable
private fun User(username: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CustomIcon(
            modifier = Modifier.size(CustomTheme.shape.tinySize),
            model = painterResource(id = R.drawable.account),
            tint = CustomTheme.color.accent
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.tinyPadding))
        Text(
            text = username,
            style = CustomTheme.typography.primaryBody,
            color = CustomTheme.color.accent
        )
    }
}

@Composable
private fun Category(category: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id = R.string.main_category),
            style = CustomTheme.typography.primaryBody,
            color = CustomTheme.color.accent
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.tinyPadding))
        Text(
            text = category,
            style = CustomTheme.typography.primaryBody
        )
    }
}

@Composable
private fun Description(description: String, isDescriptionHidden: Boolean) {
    val maxLines = if (isDescriptionHidden) {
        3
    } else {
        Int.MAX_VALUE
    }

    Text(
        text = description,
        style = CustomTheme.typography.primaryBody,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun Date(date: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = date,
            style = CustomTheme.typography.warning,
            color = CustomTheme.color.secondaryText
        )
    }
}

@Composable
fun LoadingPostMainSection() {
    LoadingTitle()
    Spacer(modifier = Modifier.height(CustomTheme.shape.tinyPadding))
    LoadingUser()
    Spacer(modifier = Modifier.height(CustomTheme.shape.tinyPadding))
    LoadingCategory()
    Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))
    LoadingDescription()
    Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))
    LoadingDate()
}

@Composable
private fun LoadingTitle() {
    LoadingPostField(
        width = CustomTheme.shape.largeSize,
        height = CustomTheme.shape.tinySize
    )
}

@Composable
private fun LoadingUser() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        LoadingPostField(
            width = CustomTheme.shape.tinySize,
            height = CustomTheme.shape.tinySize
        )
        Spacer(modifier = Modifier.width(CustomTheme.shape.tinyPadding))
        LoadingPostField(width = CustomTheme.shape.mediumSize)
    }
}

@Composable
private fun LoadingCategory() {
    Row {
        LoadingPostField(width = CustomTheme.shape.largeSize)
        Spacer(modifier = Modifier.width(CustomTheme.shape.tinyPadding))
        LoadingPostField(width = CustomTheme.shape.mediumSize)
    }
}

@Composable
private fun LoadingDescription() {
    LoadingPostField()
    Spacer(modifier = Modifier.height(CustomTheme.shape.tinyPadding))
    LoadingPostField()
    Spacer(modifier = Modifier.height(CustomTheme.shape.tinyPadding))
    LoadingPostField()
}

@Composable
private fun LoadingDate() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        LoadingPostField(width = CustomTheme.shape.largeSize)
    }
}