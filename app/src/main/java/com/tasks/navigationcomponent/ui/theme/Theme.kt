package com.tasks.navigationcomponent.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorPalette = darkColors(
    primary = L_background,
    primaryVariant = L_cardBackground,
    secondary = L_bigFont
)

private val DarkColorPalette = lightColors(
    primary = D_background,
    primaryVariant = D_cardBackground,
    secondary = D_bigFont
)

@Composable
fun NavigationComponentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}