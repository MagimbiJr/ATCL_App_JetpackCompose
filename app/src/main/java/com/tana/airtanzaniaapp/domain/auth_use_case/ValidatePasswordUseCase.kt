package com.tana.airtanzaniaapp.domain.auth_use_case

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "Please enter password"
            )
        }
        val hasNumberAndLetter = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if (!hasNumberAndLetter) {
            return ValidationResult(
                successful = false,
                message = "Password must contains numbers and letters"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}