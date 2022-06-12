package com.tana.airtanzaniaapp.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tana.airtanzaniaapp.presentation.home.HomeUiState

@Composable
fun HomeBottom(
    uiState: HomeUiState,
    onBookFlightClick: () -> Unit,
    onRentCarClick: () -> Unit,
    onHotelClick: () -> Unit,
    onTripClick: () -> Unit,
    onViewAllClick: () -> Unit,
    modifier: Modifier
) {
    Column() {
        Spacer(modifier = modifier.height(35.dp))
        Services(
            onBookFlightClick = onBookFlightClick,
            onRentCarClick = onRentCarClick,
            onHotelClick = onHotelClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(50.dp))
        TripList(
            uiState = uiState,
            onClick = onTripClick,
            modifier = modifier,
            onViewAllClick = onViewAllClick
        )
    }
}