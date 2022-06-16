package com.tana.airtanzaniaapp.presentation.authentication.register

data class RegisterUiState(
    val loading: Boolean = false,
    val credentials: RegCredentials = RegCredentials(),
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val firstNameErrorMessage: String? = null,
    val lastNameErrorMessage: String? = null,
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,
    val verifyPasswordErrorMessage: String? = null,
    val checked: Boolean = false,
    val checkedErrorMessage: String? = null
)
