package com.tana.airtanzaniaapp.domain.auth_use_case

class ValidateTermsCheckUseCase {

    operator fun invoke(checked: Boolean): ValidationResult {
        if (!checked) {
            return ValidationResult(
                successful = false,
                message = "Please accept privacy policy"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}