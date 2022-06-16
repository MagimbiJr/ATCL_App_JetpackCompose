package com.tana.airtanzaniaapp.domain.auth_use_case

class ValidateFirstNameUseCase {

    operator fun invoke(firstName: String): ValidationResult {
        if (firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "Please enter first name"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}