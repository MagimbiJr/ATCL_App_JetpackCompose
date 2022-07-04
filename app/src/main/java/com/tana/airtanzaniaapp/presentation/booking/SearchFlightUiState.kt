package com.tana.airtanzaniaapp.presentation.booking

import android.os.Build
import androidx.annotation.RequiresApi
import com.tana.airtanzaniaapp.data.Flight
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class SearchFlightUiState (
    val loading: Boolean = false,
    val result: List<Flight> = emptyList(),
    val errorMessage: String? = null,
    val fromTextField: String = "",
    val toTextField: String = "",
    val scheduledDate: LocalDate = LocalDate.now(),
    val passengersTextField: String = "",
    val classTextField: String = ""
)