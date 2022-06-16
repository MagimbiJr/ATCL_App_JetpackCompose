package com.tana.airtanzaniaapp.presentation.authentication.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.airtanzaniaapp.data.repository.AuthRepository
import com.tana.airtanzaniaapp.domain.auth_use_case.*
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
class RegisterViewModel @Inject constructor(
    private val validateFirstName: ValidateFirstNameUseCase,
    private val validateLastName: ValidateLastNameUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateVerifyPassword: ValidateVerifyPasswordUseCase,
    private val validateTermsCheck: ValidateTermsCheckUseCase,
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvent = Channel<ATCAppEvents>()
    val appEvent = _appEvent.receiveAsFlow()

    fun register() {
        val currentCredentials = _uiState.value.credentials
        val firstNameResult = validateFirstName.invoke(currentCredentials.firstName)
        val lastNameResult = validateLastName.invoke(currentCredentials.lastName)
        val emailResult = validateEmail.invoke(currentCredentials.email)
        val passwordResult = validatePassword.invoke(currentCredentials.password)
        val verifyPasswordResult = validateVerifyPassword.invoke(
            currentCredentials.password,
            currentCredentials.verifyPassword
        )
        val termsResult = validateTermsCheck.invoke(_uiState.value.checked)

        val hasError = listOf(
            firstNameResult, lastNameResult,
            emailResult, passwordResult,
            verifyPasswordResult, termsResult
        ).any { !it.successful }

        if (hasError) {
            _uiState.value = _uiState.value.copy(
                firstNameErrorMessage = firstNameResult.message,
                lastNameErrorMessage = lastNameResult.message,
                emailErrorMessage = emailResult.message,
                passwordErrorMessage = passwordResult.message,
                verifyPasswordErrorMessage = verifyPasswordResult.message,
                checkedErrorMessage = termsResult.message
            )
            return
        }

        _uiState.value = _uiState.value.copy(
            loading = _uiState.value.errorMessage == null ||
                    _uiState.value.successMessage == null
        )
        viewModelScope.launch {

            repository.register(
                firstName = currentCredentials.firstName.trim(),
                lastName = currentCredentials.lastName.trim(),
                email = currentCredentials.email.trim(),
                password = currentCredentials.password.trim()
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            successMessage = response.data?.message
                        )
                        _appEvent.send(
                            ATCAppEvents.ShowSnackBar(
                                response.data?.message ?: "No message from server"
                            )
                        )
                        _appEvent.send(ATCAppEvents.Navigate("bottom_nav"))
                    }
                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = response.message
                        )
                    }
                    is Resource.Loading -> Unit
                }
                _uiState.value = _uiState.value.copy(loading = false)
            }
        }
    }

    fun onFirstNameChange(firstName: String) {
        val currentCredentials = _uiState.value.credentials
        _uiState.value = _uiState.value.copy(
            credentials = currentCredentials.copy(firstName = firstName),
            firstNameErrorMessage = null
        )
    }
    fun onLastNameChange(lastName: String){
        val currentCredentials = _uiState.value.credentials
        _uiState.value = _uiState.value.copy(
            credentials = currentCredentials.copy(lastName = lastName),
            lastNameErrorMessage = null
        )
    }

    fun onEmailChange(email: String){
        val currentCredentials = _uiState.value.credentials
        _uiState.value = _uiState.value.copy(
            credentials = currentCredentials.copy(email = email),
            emailErrorMessage = null
        )
    }

    fun onPasswordChange(password: String){
        val currentCredentials = _uiState.value.credentials
        _uiState.value = _uiState.value.copy(
            credentials = currentCredentials.copy(password = password),
            passwordErrorMessage = null
        )
    }

    fun onVerifyPasswordChange(verifyPassword: String){
        val currentCredentials = _uiState.value.credentials
        _uiState.value = _uiState.value.copy(
            credentials = currentCredentials.copy(verifyPassword = verifyPassword),
            verifyPasswordErrorMessage = null
        )
    }
    fun onCheckChange(checked: Boolean){
        _uiState.value = _uiState.value.copy(
            checked = checked,
            checkedErrorMessage = null
        )
    }
    fun navigateToLogin() {
        viewModelScope.launch {
            _appEvent.send(ATCAppEvents.Navigate("login"))
        }
    }

    fun navigateToPrivacyPolicy() {
        viewModelScope.launch {
            _appEvent.send(ATCAppEvents.Navigate("privacy_policy"))
        }
    }
}