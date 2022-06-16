package com.tana.airtanzaniaapp.presentation.authentication.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.tana.airtanzaniaapp.data.repository.AuthRepository
import com.tana.airtanzaniaapp.domain.auth_use_case.ValidateEmailUseCase
import com.tana.airtanzaniaapp.domain.auth_use_case.ValidatePasswordUseCase
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
class LoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvent = Channel<ATCAppEvents>()
    val appEvent = _appEvent.receiveAsFlow()

    fun login() {
        val currentCredentials = _uiState.value.credentials
        val emailResult = validateEmail.invoke(currentCredentials.email)
        val passwordResult = validatePassword.invoke(currentCredentials.password)

        val hasError = listOf(
            emailResult, passwordResult
        ).any { !it.successful }

        if (hasError) {
            _uiState.value = _uiState.value.copy(emailErrorMessage = emailResult.message)
            _uiState.value = _uiState.value.copy(passwordErrorMessage = passwordResult.message)
            return
        }
        _uiState.value = _uiState.value.copy(
            loading = _uiState.value.errorMessage == null ||
                    _uiState.value.successMessage == null
        )
        viewModelScope.launch {
            repository.login(
                email = currentCredentials.email.trim(),
                password = currentCredentials.password.trim()
            ).collect { response ->
                when(response) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            successMessage = response.data?.message
                        )
                        _appEvent.send(ATCAppEvents.ShowSnackBar(response.data?.message ?: "No message"))
                        _appEvent.send(ATCAppEvents.Navigate("bottom_nav"))
                    }
                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = response.message
                        )
                    }
                    is Resource.Loading -> Unit
                }
                _uiState.value = _uiState.value.copy(
                    loading = false
                )
            }
        }
    }

    fun onEmailChange(email: String) {
        val currentCredentials = _uiState.value.credentials
        _uiState.value = _uiState.value.copy(
            credentials = currentCredentials.copy(email = email),
            emailErrorMessage = null
        )
    }

    fun onPasswordChange(password: String) {
        val currentCredentials = _uiState.value.credentials
        _uiState.value = _uiState.value.copy(
            credentials = currentCredentials.copy(password = password),
            passwordErrorMessage = null
        )
    }

    fun navigateToRegister() {
        viewModelScope.launch {
            _appEvent.send(ATCAppEvents.Navigate("register"))
        }
    }

    fun navigateToRecoverPassword() {
        viewModelScope.launch {
            _appEvent.send(ATCAppEvents.Navigate("recover_password"))
        }
    }

}