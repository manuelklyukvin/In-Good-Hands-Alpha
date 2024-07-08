package com.klyukvin.ingoodhands.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klyukvin.ingoodhands.navigation.NavigationState
import com.klyukvin.ingoodhands.navigation.rememberNavigationState
import com.klyukvin.ingoodhands.ui.theme.resource.Colors
import com.klyukvin.ingoodhands.ui.theme.resource.Fonts

val LocalAppNavigationState = compositionLocalOf<NavigationState> {
    error("No AppNavigationState provided")
}

val LocalMainNavigationState = compositionLocalOf<NavigationState> {
    error("No MainNavigationState provided")
}

private val localColors = staticCompositionLocalOf<CustomColors> {
    error("No colors provided")
}

private val localTypography = staticCompositionLocalOf<CustomTypography> {
    error("No typography provided")
}

private val localShapes = staticCompositionLocalOf<CustomShapes> {
    error("No shapes provided")
}

@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    val colors = CustomColors(
        accent = Colors.green,
        primaryBackground = Colors.lightGray,
        secondaryBackground = Colors.white,
        primaryText = Colors.black,
        secondaryText = Colors.darkGray,
        warning = Colors.red,
        primaryButtonContent = Colors.white,
        primaryButtonContainer = Colors.green,
        secondaryButtonContent = Colors.black,
        secondaryButtonContainer = Colors.white,
        primaryDisabledButtonContent = Colors.lightGray,
        primaryDisabledButtonContainer = Colors.darkGreen,
        secondaryDisabledButtonContent = Colors.white,
        secondaryDisabledButtonContainer = Colors.darkGray,
        textFieldText = Colors.black,
        textFieldHint = Colors.darkGray,
        textFieldBackground = Colors.gray
    )

    val typography = CustomTypography(
        primaryHeading = TextStyle(
            fontFamily = Fonts.ptSans,
            fontSize = 25.sp,
            color = colors.accent
        ),
        secondaryHeading = TextStyle(
            fontFamily = Fonts.ptSans,
            fontSize = 25.sp,
            color = colors.primaryText
        ),
        primaryBody = TextStyle(
            fontFamily = Fonts.ptSans,
            fontSize = 15.sp,
            color = colors.primaryText
        ),
        secondaryBody = TextStyle(
            fontFamily = Fonts.ptSans,
            fontSize = 15.sp,
            color = colors.secondaryText
        ),
        warning = TextStyle(
            fontFamily = Fonts.ptSans,
            fontSize = 12.sp,
            color = colors.warning
        ),
        buttonText = TextStyle(
            fontFamily = Fonts.ptSans,
            fontSize = 15.sp,
            color = colors.primaryButtonContent
        ),
        textFieldText = TextStyle(
            fontFamily = Fonts.ptSans,
            fontSize = 15.sp,
            color = colors.textFieldText
        ),
        textFieldHint = TextStyle(
            fontFamily = Fonts.ptSans,
            fontSize = 15.sp,
            color = colors.textFieldHint
        )
    )

    val shapes = CustomShapes(
        verticalScreenPadding = 50.dp,
        horizontalScreenPadding = 20.dp,
        tinyPadding = 5.dp,
        smallPadding = 10.dp,
        mediumPadding = 15.dp,
        largePadding = 20.dp,
        tinySize = 35.dp,
        smallSize = 50.dp,
        mediumSize = 75.dp,
        largeSize = 100.dp,
        linePadding = 10.dp,
        corners = 10.dp,
        imageHeight = 300.dp
    )

    CompositionLocalProvider(
        LocalAppNavigationState provides rememberNavigationState(),
        LocalMainNavigationState provides rememberNavigationState(),
        localColors provides colors,
        localTypography provides typography,
        localShapes provides shapes,
        content = content
    )
}

object CustomTheme {

    val color: CustomColors
        @Composable
        get() = localColors.current

    val typography: CustomTypography
        @Composable
        get() = localTypography.current

    val shape: CustomShapes
        @Composable
        get() = localShapes.current
}