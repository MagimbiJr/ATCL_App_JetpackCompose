package com.tana.airtanzaniaapp.presentation.authentication.register

data class RegCredentials(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val verifyPassword: String = ""
)
