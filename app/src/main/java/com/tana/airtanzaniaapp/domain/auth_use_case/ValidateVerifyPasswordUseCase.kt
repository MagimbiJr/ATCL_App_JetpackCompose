package com.tana.airtanzaniaapp.domain.auth_use_case

class ValidateVerifyPasswordUseCase {

    operator fun invoke(password: String, verifyPassword: String): ValidationResult {
        if (password != verifyPassword) {
            return ValidationResult(
                successful = false,
                message = "Password don't match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}