package com.tana.airtanzaniaapp.presentation.booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.airtanzaniaapp.data.Flight
import com.tana.airtanzaniaapp.data.repository.BookingRepository
import com.tana.airtanzaniaapp.util.ATCAppEvents
import com.tana.airtanzaniaapp.util.Resource
import com.tana.airtanzaniaapp.util.toUiString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val repository: BookingRepository
) : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(SearchFlightUiState())
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState = _uiState.asStateFlow()
    private val _appState = Channel<ATCAppEvents>()
    val appState = _appState.receiveAsFlow()

//    init {
//        searchFlights()
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun searchFlights() {
        _uiState.value = _uiState.value.copy(
            loading = _uiState.value.result.isEmpty() ||
                    _uiState.value.errorMessage == null
        )
        viewModelScope.launch {
            repository.searchFlight(
                from = _uiState.value.fromTextField.trim(),
                to = _uiState.value.toTextField.trim(),
                date = _uiState.value.scheduledDate.toUiString(),
            ).collect { response ->
                when(response) {
                    is Resource.Success -> {
                        _uiState.value = SearchFlightUiState(
                            result = response.data ?: emptyList()
                        )
                    }
                    is Resource.Failure -> {
                        _uiState.value = SearchFlightUiState(
                            errorMessage = response.message
                        )
                    }
                    is Resource.Loading -> Unit
                }
            }
        }
        _uiState.value = _uiState.value.copy(
            loading = false
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onFromFieldChange(from: String) {
        _uiState.value = _uiState.value.copy(
            fromTextField = from
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onToFieldChange(to: String) {
        _uiState.value = _uiState.value.copy(
            toTextField = to
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onDateFieldChange(date: LocalDate) {
        _uiState.value = _uiState.value.copy(
            scheduledDate = date
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onPassengerFieldChange(passengers: String) {
        _uiState.value = _uiState.value.copy(
            passengersTextField = passengers
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClassFieldChange(classType: String) {
        _uiState.value = _uiState.value.copy(
            classTextField = classType
        )
    }

    fun navigateToSelectSeat(flight: Flight) {
        viewModelScope.launch {
            _appState.send(ATCAppEvents.Navigate("select_seat_screen/${flight.flightId}"))
        }
    }

}