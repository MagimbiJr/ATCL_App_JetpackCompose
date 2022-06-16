package com.tana.airtanzaniaapp.presentation.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.presentation.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController,
    systemUiController: SystemUiController,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    systemUiController.setSystemBarsColor(Color.Black)
    val pagerState = rememberPagerState()
    val pages = listOf(
        OnboardingPage.First(
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(
                        page = 1,
                    )
                }
            }
        ),
        OnboardingPage.Second(
            onClick = {
                coroutineScope.launch { pagerState.animateScrollToPage(2) }
            }
        ),
        OnboardingPage.Third(
            onClick = {
                viewModel.saveOnBoardingState(true)
                navHostController.popBackStack()
                navHostController.navigate("bottom_nav")
            }
        )
    )

    HorizontalPager(count = pages.size, state = pagerState) { page ->
        PageScreen(page = pages[page], pagerState = pagerState, modifier = modifier)
    }
}