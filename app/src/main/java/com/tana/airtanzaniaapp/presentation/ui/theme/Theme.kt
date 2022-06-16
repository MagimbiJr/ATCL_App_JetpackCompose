package com.tana.airtanzaniaapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = GreenBlue,
    surface = Onyx,
    background = EerieBlack,
    onBackground = Cultured,
    onSurface = Cultured,
)

private val LightColorPalette = lightColors(
    primary = GreenBlue,
    secondary = Aero,
    surface = Color.White,
    background = Cultured,
    onSurface = EerieBlack,
    onBackground = EerieBlack
)

@Composable
fun AirTanzaniaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}