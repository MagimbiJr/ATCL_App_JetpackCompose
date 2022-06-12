package com.tana.airtanzaniaapp.presentation.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = GreenBlue,
    secondary = Onyx,
    surface = Onyx,
    background = EerieBlack,
    onBackground = Color.White,
    onSurface = Color.White
)

//private val ColorPalette = lightColors(
//    primary = GreenBlue,
//    background = Cultured,
//    surface = Color.White,
//    secondary = Aero
//)

private val LightColorPalette = lightColors(
    primary = GreenBlue,
    secondary = Aero,
    surface = Color.White,
    background = Cultured,
    onSurface = EerieBlack,
    onBackground = EerieBlack

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
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