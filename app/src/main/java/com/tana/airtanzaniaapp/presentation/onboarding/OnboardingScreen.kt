package com.tana.airtanzaniaapp.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navHostController: NavHostController,
    systemUiController: SystemUiController,
    coroutineScope: CoroutineScope,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()
    val pagerScreens = listOf(
        OnboardingPage.First(
            systemUiController = systemUiController,
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(1)
                }
            },
            pagerState = pagerState
        ),
        OnboardingPage.Second(
            systemUiController = systemUiController,
            onClick = {
                coroutineScope.launch {
                    //viewModel.saveOnBoardingState(completed = true)
                    pagerState.animateScrollToPage(2)
                }
            },
            pagerState = pagerState
        ),
        OnboardingPage.Third(
            systemUiController = systemUiController,
            onLoginClick = {
                viewModel.saveOnBoardingState(completed = true)
                navHostController.navigate("login")
            },
            onRegister = {
                viewModel.saveOnBoardingState(completed = true)
                navHostController.navigate("register")
            }
        )
    )
    Column {
        HorizontalPager(count = pagerScreens.size, state = pagerState) { position ->
            PagerScreen(onboardingPage = pagerScreens[position])
        }
    }
}