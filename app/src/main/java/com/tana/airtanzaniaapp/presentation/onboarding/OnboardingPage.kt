package com.tana.airtanzaniaapp.presentation.onboarding

import com.tana.airtanzaniaapp.R

sealed class OnboardingPage(
    val background: Int,
    val title: String,
    val description: String,
    val onClick: () -> Unit,
) {
    class First(onClick: () -> Unit) : OnboardingPage(
        background = R.drawable.landing_bg_1,
        title = "Explore the World with Us!",
        description = "Your talent determines what you can do. Your motivation determines " +
                "how much you’re willing to do. Your attitude determines how well you do it.",
        onClick = onClick
    )

    class Second(onClick: () -> Unit) : OnboardingPage(
        background = R.drawable.landing_bg_2,
        title = "Experience the travel and adventure.",
        description = "The airline that connects Tanzania more to the world than any other awaits you.",
        onClick = onClick
    )

    class Third(onClick: () -> Unit) : OnboardingPage(
        background = R.drawable.landing_bg_3,
        title = "Get Started!",
        description = "Please Log in or register to continue using AirTanzania App",
        onClick = onClick
    )
}
