package com.tana.airtanzaniaapp.presentation.home

import com.tana.airtanzaniaapp.data.Destination

data class HomeUiState(
    val loading: Boolean = false,
    val bestTrips: List<Destination> = emptyList(),
    val message: String = ""
)
