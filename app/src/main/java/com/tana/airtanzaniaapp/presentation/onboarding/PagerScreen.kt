package com.tana.airtanzaniaapp.presentation.onboarding

import androidx.compose.runtime.Composable

@Composable
fun PagerScreen(
    onboardingPage: OnboardingPage
) {
    onboardingPage.screen()
}