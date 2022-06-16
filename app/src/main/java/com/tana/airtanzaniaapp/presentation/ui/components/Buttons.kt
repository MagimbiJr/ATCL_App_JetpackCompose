package com.tana.airtanzaniaapp.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.ui.theme.EerieBlack
import com.tana.airtanzaniaapp.presentation.ui.theme.GreenBlue

@Composable
fun ATCPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = GreenBlue
) {
    val colors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = Color.White
    )
    Button(
        onClick = onClick,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height)),
        colors = colors,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text.toUpperCase(Locale.current))
    }
}

@Composable
fun ATCSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = EerieBlack
) {
    val colors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = Color.White
    )
    Button(
        onClick = onClick,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height)),
        colors = colors,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text.toUpperCase(Locale.current))
    }
}

@Composable
fun ATCTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text.toUpperCase(Locale.current))
    }
}