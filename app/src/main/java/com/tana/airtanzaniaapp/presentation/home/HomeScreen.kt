package com.tana.airtanzaniaapp.presentation.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.presentation.ui.theme.Aero
import com.tana.airtanzaniaapp.util.ATCAppEvents
import kotlinx.coroutines.flow.collect

@Composable
fun HomeScreen(
    onNavigate: (ATCAppEvents.Navigate) -> Unit,
    scrollState: ScrollState,
    systemUiController: SystemUiController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    systemUiController.setStatusBarColor(Aero)
    val color = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else
        MaterialTheme.colors.background
    systemUiController.setNavigationBarColor(color)
    val uiState = viewModel.uiState.collectAsState().value
    val searchValue = viewModel.searchValue.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.appEvents.collect { event ->
            when(event) {
                is ATCAppEvents.Navigate -> {
                    onNavigate(event)
                }
                is ATCAppEvents.PopBack -> Unit
                is ATCAppEvents.ShowSnackBar -> Unit
            }
        }
    }

    if (uiState.loading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        HomeContent(
            scrollState = scrollState,
            value = searchValue,
            uiState = uiState,
            onTripClick = viewModel::onTripClick,
            onSearch = viewModel::onSearch,
            onBookFlightClick = viewModel::onBookFlightClick,
            onRentCarClick = viewModel::onRentCarClick,
            onHotelClick = viewModel::onHotelClick,
            onViewAllClick = viewModel::onViewAllClick,
            modifier = modifier,
        )
    }

}