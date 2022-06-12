package com.tana.airtanzaniaapp.presentation.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tana.airtanzaniaapp.presentation.home.components.HomeBottom
import com.tana.airtanzaniaapp.presentation.home.components.HomeTop

@Composable
fun HomeContent(
    value: String,
    uiState: HomeUiState,
    onTripClick: () -> Unit,
    onSearch: (String) -> Unit,
    onBookFlightClick: () -> Unit,
    onRentCarClick: () -> Unit,
    onHotelClick: () -> Unit,
    onViewAllClick: () -> Unit,
    scrollState: ScrollState,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        HomeTop(
            value = value,
            onSearch = onSearch,
            modifier = modifier
        )
        HomeBottom(
            uiState = uiState,
            onBookFlightClick = onBookFlightClick,
            onRentCarClick = onRentCarClick,
            onHotelClick = onHotelClick,
            onTripClick = onTripClick,
            onViewAllClick = onViewAllClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(16.dp))
    }
}