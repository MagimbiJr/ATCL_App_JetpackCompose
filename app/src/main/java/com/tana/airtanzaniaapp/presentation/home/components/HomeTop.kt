package com.tana.airtanzaniaapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.ui.components.SearchBar

@Composable
fun HomeTop(
    value: String,
    onSearch: (String) -> Unit,
    modifier: Modifier
) {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = "background",
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 40.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = "Where are you \n flying to?",
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = modifier.height(30.dp))
            SearchBar(
                value = value,
                onSearch = onSearch,
                modifier = modifier,
                placeholder = "Search destinations",
            )
        }
    }
}