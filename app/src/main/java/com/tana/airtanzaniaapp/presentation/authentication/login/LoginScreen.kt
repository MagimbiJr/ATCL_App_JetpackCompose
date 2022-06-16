package com.tana.airtanzaniaapp.presentation.authentication.login

import androidx.compose.foundation.ScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.focus.FocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.util.ATCAppEvents
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(
    onNavigate: (ATCAppEvents.Navigate) -> Unit,
    systemUiController: SystemUiController,
    scaffoldState: ScaffoldState,
    focusManager: FocusManager,
    scrollState: ScrollState,
    viewModel: LoginViewModel = hiltViewModel()
) {
    systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.appEvent.collect { event ->
            when(event) {
                is ATCAppEvents.Navigate -> { onNavigate(event)}
                is ATCAppEvents.ShowSnackBar -> { scaffoldState.snackbarHostState.showSnackbar(event.message)}
                is ATCAppEvents.PopBack -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        LoginContent(
            uiState = uiState,
            onEmailChanged = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onLoginClick = viewModel::login,
            onRegisterClick = viewModel::navigateToRegister,
            onRecoverPasswordClick = viewModel::navigateToRecoverPassword,
            focusManager = focusManager,
            scrollState = scrollState
        )
    }
}