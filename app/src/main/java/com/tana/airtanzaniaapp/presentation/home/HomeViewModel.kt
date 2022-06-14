package com.tana.airtanzaniaapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.tana.airtanzaniaapp.data.PreferencesManager
import com.tana.airtanzaniaapp.data.repository.ATCRepository
import com.tana.airtanzaniaapp.data.repository.ATCRepositoryImpl
import com.tana.airtanzaniaapp.util.ATCAppEvents
import com.tana.airtanzaniaapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ATCRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvents = Channel<ATCAppEvents>()
    val appEvents = _appEvents.receiveAsFlow()
    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    init {
        bestTrips()
    }

    private fun bestTrips() {

        viewModelScope.launch {
            repository.bestTrips().collect { response ->
                when(response) {
                    is Resource.Success -> {
                        _uiState.value = HomeUiState(bestTrips = response.data ?: emptyList())
                    }
                    is Resource.Failure -> {
                        _uiState.value = HomeUiState(message = response.message ?: "")
                    }
                    is Resource.Loading -> {
                        _uiState.value = HomeUiState(loading = true)
                    }
                }
            }
        }
    }

    fun onSearch(value: String) {
        _searchValue.value = value
    }

    fun onTripClick() {
        viewModelScope.launch {
            _appEvents.send(ATCAppEvents.Navigate("trip_detail"))
        }
    }

    fun onBookFlightClick() {
        viewModelScope.launch {
            _appEvents.send(ATCAppEvents.Navigate("book_flight_screen"))
        }
    }

    fun onRentCarClick() {
        viewModelScope.launch {
            _appEvents.send(ATCAppEvents.Navigate("rent_car_screen"))
        }
    }

    fun onHotelClick() {
        viewModelScope.launch {
            _appEvents.send(ATCAppEvents.Navigate("hotels_screen"))
        }
    }

    fun onViewAllClick() {
        viewModelScope.launch {
            _appEvents.send(ATCAppEvents.Navigate("trip_list"))
        }
    }
}