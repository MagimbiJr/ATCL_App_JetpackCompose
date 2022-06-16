package com.tana.airtanzaniaapp.domain.auth_use_case

data class ValidationResult(
    val successful: Boolean,
    val message: String? = null
)
