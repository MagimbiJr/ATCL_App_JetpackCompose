package com.tana.airtanzaniaapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tana.airtanzaniaapp.R

@Composable
fun SearchBar(
    value: String,
    onSearch: (String) -> Unit,
    modifier: Modifier,
    placeholder: String,
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = backgroundColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
    TextField(
        value = value,
        onValueChange = onSearch,
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.location_outline_icon),
                contentDescription = "Location icon",
                modifier = modifier
                    .size(18.dp)
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search icon",
                modifier = modifier
                    .size(18.dp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = colors
    )
}