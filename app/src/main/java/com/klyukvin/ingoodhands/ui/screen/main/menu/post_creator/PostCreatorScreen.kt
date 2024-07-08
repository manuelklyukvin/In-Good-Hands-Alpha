package com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klyukvin.ingoodhands.R
import com.klyukvin.ingoodhands.ui.component.ScreenBlocking
import com.klyukvin.ingoodhands.ui.component.custom.CustomButton
import com.klyukvin.ingoodhands.ui.component.custom.CustomCard
import com.klyukvin.ingoodhands.ui.component.custom.CustomIcon
import com.klyukvin.ingoodhands.ui.component.custom.CustomImage
import com.klyukvin.ingoodhands.ui.component.custom.CustomTextField
import com.klyukvin.ingoodhands.ui.component.custom.CustomTopBar
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorCategory
import com.klyukvin.ingoodhands.ui.screen.main.menu.post_creator.model.UiPostCreatorState
import com.klyukvin.ingoodhands.ui.theme.CustomTheme
import com.klyukvin.ingoodhands.ui.theme.LocalMainNavigationState
import com.klyukvin.ingoodhands.ui.theme.resource.Colors
import com.klyukvin.ingoodhands.ui.theme.resource.Shapes

@Composable
fun PostCreatorScreen() {
    val viewModel = hiltViewModel<PostCreatorViewModel>()
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBar()
        Spacer(modifier = Modifier.height(CustomTheme.shape.smallPadding))
        Box(modifier = Modifier.fillMaxSize()) {
            Content(viewModel)
            if (state is UiPostCreatorState.Loading) {
                ScreenBlocking()
            }
        }
    }
}

@Composable
private fun Content(viewModel: PostCreatorViewModel) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = CustomTheme.shape.horizontalScreenPadding)
            .clip(Shapes.defaultRoundedShape),
        verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)
    ) {
        item {
            MainSection(viewModel)
        }
        item {
            Category(viewModel)
        }
        item {
            Contacts(viewModel)
        }
        item {
            CreatePostButton(viewModel)
        }
    }
}

@Composable
private fun MainSection(viewModel: PostCreatorViewModel) {
    CustomCard(
        useDefaultPaddings = true,
        verticalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding)
    ) {
        ImageButton(viewModel)
        SelectedImages(viewModel)
        Title(viewModel)
    }
}

@Composable
private fun ImageButton(viewModel: PostCreatorViewModel) {
    val errors by viewModel.errors.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { imageUri ->
            viewModel.addImageToList(imageUri)
        }
    )

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier
                    .size(CustomTheme.shape.largeSize)
                    .background(
                        color = CustomTheme.color.textFieldBackground,
                        shape = Shapes.defaultRoundedShape
                    ),
                onClick = {
                    viewModel.onAddImageButtonClicked(launcher)
                }
            ) {
                CustomIcon(
                    modifier = Modifier.size(CustomTheme.shape.smallSize),
                    model = painterResource(id = R.drawable.add),
                    tint = CustomTheme.color.accent
                )
            }
            Spacer(modifier = Modifier.width(CustomTheme.shape.largePadding))
            Text(
                text = stringResource(id = R.string.post_creator_image),
                style = CustomTheme.typography.secondaryBody
            )
        }
        if (errors.imagesError != null) {
            Text(
                text = errors.imagesError!!,
                style = CustomTheme.typography.warning
            )
        }
    }
}

@Composable
private fun SelectedImages(viewModel: PostCreatorViewModel) {
    val fields by viewModel.fields.collectAsState()
    val isSelectedImagesShown by viewModel.isSelectedImagesShown.collectAsState()

    if (isSelectedImagesShown) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(CustomTheme.shape.linePadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(fields.imageUris) { image ->
                SelectedImage(viewModel, image)
            }
        }
    }
}

@Composable
private fun SelectedImage(viewModel: PostCreatorViewModel, image: Uri) {
    Box(contentAlignment = Alignment.TopEnd) {
        CustomImage(
            modifier = Modifier
                .size(CustomTheme.shape.mediumSize)
                .clip(Shapes.defaultRoundedShape),
            model = image,
            contentScale = ContentScale.Crop
        )
        DeleteImageButton(viewModel, image)
    }
}

@Composable
private fun DeleteImageButton(viewModel: PostCreatorViewModel, image: Uri) {
    IconButton(
        modifier = Modifier
            .padding(CustomTheme.shape.tinyPadding)
            .background(
                color = Colors.transparentBlack,
                shape = RoundedCornerShape(20.dp)
            )
            .size(20.dp),
        onClick = {
            viewModel.deleteImageFromList(image)
        }
    ) {
        CustomIcon(
            modifier = Modifier.size(15.dp),
            model = painterResource(id = R.drawable.close),
            tint = Colors.white
        )
    }
}

@Composable
private fun Title(viewModel: PostCreatorViewModel) {
    val fields by viewModel.fields.collectAsState()
    val errors by viewModel.errors.collectAsState()

    CustomTextField(
        value = fields.title,
        onValueChange = { title ->
            viewModel.onTitleChanged(title)
        },
        label = stringResource(id = R.string.post_creator_title),
        placeholder = stringResource(id = R.string.post_creator_title_placeholder),
        error = errors.titleError
    )
    CustomTextField(
        contentModifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        value = fields.description,
        onValueChange = { description ->
            viewModel.onDescriptionChanged(description)
        },
        label = stringResource(id = R.string.post_creator_description),
        placeholder = stringResource(id = R.string.post_creator_description_placeholder),
        error = errors.descriptionError,
        singleLine = false
    )
}

@Composable
private fun Category(viewModel: PostCreatorViewModel) {
    val fields by viewModel.fields.collectAsState()
    val errors by viewModel.errors.collectAsState()

    CustomCard(
        modifier = Modifier.fillMaxWidth(),
        useDefaultPaddings = true
    ) {
        Text(
            text = stringResource(id = R.string.post_creator_category),
            style = CustomTheme.typography.secondaryBody
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = fields.category.nameId),
                style = CustomTheme.typography.primaryBody
            )
            CategoryMenu(viewModel)
            IconButton(
                onClick = {
                    viewModel.onCategoriesMenuButtonClicked()
                }
            ) {
                CustomIcon(
                    modifier = Modifier.size(CustomTheme.shape.tinySize),
                    model = painterResource(id = R.drawable.arrow_down),
                    tint = CustomTheme.color.accent
                )
            }
        }
        if (errors.categoryError != null) {
            Text(
                text = errors.categoryError!!,
                style = CustomTheme.typography.warning
            )
        }
    }
}

@Composable
private fun CategoryMenu(viewModel: PostCreatorViewModel) {
    val isCategoryMenuExpanded by viewModel.isCategoryMenuExpanded.collectAsState()
    val categoriesList = listOf(
        UiPostCreatorCategory.Cats,
        UiPostCreatorCategory.Dogs,
        UiPostCreatorCategory.Rodents,
        UiPostCreatorCategory.Birds,
        UiPostCreatorCategory.Fishes,
        UiPostCreatorCategory.Reptiles,
        UiPostCreatorCategory.Other
    )

    DropdownMenu(
        modifier = Modifier
            .height(300.dp)
            .background(CustomTheme.color.secondaryBackground),
        expanded = isCategoryMenuExpanded,
        onDismissRequest = {
            viewModel.onCategoriesMenuButtonClicked()
        }
    ) {
        categoriesList.forEach { category ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = category.nameId),
                        style = CustomTheme.typography.primaryBody
                    )
                },
                onClick = {
                    viewModel.onCategoryChanged(category)
                }
            )
        }
    }
}

@Composable
private fun Contacts(viewModel: PostCreatorViewModel) {
    val fields by viewModel.fields.collectAsState()
    val errors by viewModel.errors.collectAsState()

    CustomCard(useDefaultPaddings = true) {
        CustomTextField(
            value = fields.phoneNumber,
            onValueChange = { phoneNumber ->
                viewModel.onPhoneNumberChanged(phoneNumber)
            },
            label = stringResource(id = R.string.post_creator_phone_number),
            placeholder = stringResource(id = R.string.post_creator_phone_number_placeholder),
            error = errors.phoneNumberError,
            keyboardType = KeyboardType.Phone
        )
        Spacer(modifier = Modifier.height(CustomTheme.shape.linePadding))
        CustomTextField(
            value = fields.address,
            onValueChange = { address ->
                viewModel.onAddressChanged(address)
            },
            label = stringResource(id = R.string.post_creator_address),
            placeholder = stringResource(id = R.string.post_creator_address_placeholder),
            error = errors.addressError
        )
    }
}

@Composable
private fun CreatePostButton(viewModel: PostCreatorViewModel) {
    val state by viewModel.state.collectAsState()
    val isButtonEnabled by viewModel.isButtonEnabled.collectAsState()
    val navigationState = LocalMainNavigationState.current

    CustomButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            viewModel.onCreatePostButtonClicked(navigationState)
        },
        text = if (state !is UiPostCreatorState.Loading) {
            stringResource(id = R.string.post_creator_button)
        } else {
            null
        },
        content = {
            if (state is UiPostCreatorState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = CustomTheme.color.primaryDisabledButtonContent,
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Round
                )
            }
        },
        enabled = isButtonEnabled && state !is UiPostCreatorState.Loading,
        contentColor = CustomTheme.color.primaryButtonContent,
        containerColor = CustomTheme.color.primaryButtonContainer,
        disabledContentColor = CustomTheme.color.primaryDisabledButtonContent,
        disabledContainerColor = CustomTheme.color.primaryDisabledButtonContainer
    )
}