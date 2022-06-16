package com.tana.airtanzaniaapp.domain.auth_use_case

class ValidateLastNameUseCase {

    operator fun invoke(lastName: String): ValidationResult {
        if (lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "Please enter last name"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}