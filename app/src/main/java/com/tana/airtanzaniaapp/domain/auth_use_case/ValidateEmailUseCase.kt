package com.tana.airtanzaniaapp.domain.auth_use_case

import android.util.Patterns

class ValidateEmailUseCase {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "Please enter an email"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                message = "Invalid credential"
            )
        }
        return ValidationResult(successful = true)
    }

}