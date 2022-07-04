package com.tana.airtanzaniaapp.presentation.booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.tana.airtanzaniaapp.presentation.booking.components.ResultContent
import com.tana.airtanzaniaapp.presentation.booking.components.SearchContent
import com.tana.airtanzaniaapp.util.ATCAppEvents

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingScreen(
    focusManager: FocusManager,
    onNavigate: (ATCAppEvents.Navigate) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.appState.collect { event ->
            when(event) {
                is ATCAppEvents.Navigate -> { onNavigate(event) }
                is ATCAppEvents.PopBack -> Unit
                is ATCAppEvents.ShowSnackBar -> Unit
             }
        }
    }

    Scaffold {
        if (uiState.loading) {
            CircularProgressIndicator()
        } else {
            if (uiState.result.isEmpty()) {
                SearchContent(
                    uiState = uiState,
                    errorMessage = uiState.errorMessage,
                    focusManager = focusManager,
                    onFromTextFieldChange = viewModel::onFromFieldChange,
                    onToTextFieldChange = viewModel::onToFieldChange,
                    onDateScheduleChange = {
                        viewModel.onDateFieldChange(it)
                    },
                    onPassengersTextFieldChange = viewModel::onPassengerFieldChange,
                    onClassTextFieldChange = viewModel::onClassFieldChange,
                    onSearchFlight = viewModel::searchFlights,
                    modifier = modifier
                )
            } else {
                ResultContent(
                    uiState = uiState,
                    onFlightClick = viewModel::navigateToSelectSeat
                )
            }
        }
    }
}

