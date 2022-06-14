package com.tana.airtanzaniaapp.presentation.onboarding

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.presentation.onboarding.components.screens.OnboardingOne
import com.tana.airtanzaniaapp.presentation.onboarding.components.screens.OnboardingThree
import com.tana.airtanzaniaapp.presentation.onboarding.components.screens.OnboardingTwo

sealed class OnboardingPage(val screen: @Composable () -> Unit) {
    @OptIn(ExperimentalPagerApi::class)
    class First (
        val systemUiController: SystemUiController,
        val onClick: () -> Unit,
        val pagerState: PagerState
        ) : OnboardingPage(screen = {
        OnboardingOne(
            onNextClick = onClick,
            systemUiController = systemUiController,
            pagerState = pagerState
        )
    }
    )
    @OptIn(ExperimentalPagerApi::class)
    class Second(
        val systemUiController: SystemUiController,
        val onClick: () -> Unit,
        val pagerState: PagerState
    ) : OnboardingPage(
        screen = { 
            OnboardingTwo(
                onNextClick = onClick,
                systemUiController = systemUiController,
                pagerState = pagerState
            )
        }
    )

    class Third(
        val systemUiController: SystemUiController,
        val onLoginClick: () -> Unit,
        val onRegister: () -> Unit
    ) : OnboardingPage(
        screen = {
            OnboardingThree(
                systemUiController = systemUiController,
                onLoginClick = onLoginClick,
                onRegisterClick = onRegister
            )
        }
    )
}