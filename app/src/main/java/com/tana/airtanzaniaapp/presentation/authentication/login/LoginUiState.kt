package com.tana.airtanzaniaapp.presentation.authentication.login

data class LoginUiState(
    val loading: Boolean = false,
    val credentials: LoginCredentials = LoginCredentials(),
    val errorMessage: String? = null,
    val successMessage:String? = null,
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,
)
