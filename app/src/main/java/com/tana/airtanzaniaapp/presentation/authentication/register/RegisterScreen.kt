package com.tana.airtanzaniaapp.presentation.authentication.register

import androidx.compose.foundation.ScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.util.ATCAppEvents
import kotlinx.coroutines.flow.collect

@Composable
fun RegisterScreen(
    onNavigate: (ATCAppEvents.Navigate) -> Unit,
    systemUiController: SystemUiController,
    focusManager: FocusManager,
    scrollState: ScrollState,
    scaffoldState: ScaffoldState,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.appEvent.collect { event ->
            when(event) {
                is ATCAppEvents.Navigate -> { onNavigate(event) }
                is ATCAppEvents.ShowSnackBar -> { scaffoldState.snackbarHostState.showSnackbar(event.message)}
                is ATCAppEvents.PopBack -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        RegisterContent(
            uiState = uiState,
            onFirstNameChange = viewModel::onFirstNameChange,
            onLastNameChange = viewModel::onLastNameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onVerifyPasswordChange = viewModel::onVerifyPasswordChange,
            onChecked = viewModel::onCheckChange,
            onRegisterBtnClick = viewModel::register,
            onLoginClick = viewModel::navigateToLogin,
            onPrivacyPolicyClick = viewModel::navigateToPrivacyPolicy,
            scrollState = scrollState,
            focusManager = focusManager
        )
    }
}