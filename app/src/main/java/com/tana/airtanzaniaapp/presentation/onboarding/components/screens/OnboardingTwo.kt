package com.tana.airtanzaniaapp.presentation.onboarding.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.onboarding.components.OnboardingContent
import com.tana.airtanzaniaapp.presentation.ui.theme.AirTanzaniaAppTheme
import com.tana.airtanzaniaapp.presentation.ui.theme.EerieBlack
import com.tana.airtanzaniaapp.presentation.ui.theme.GrayX11Gray

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingTwo(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    systemUiController: SystemUiController,
    pagerState: PagerState
) {
    systemUiController.setStatusBarColor(GrayX11Gray.copy(.52f))
    systemUiController.setNavigationBarColor(EerieBlack)
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.landing_bg_2),
            contentDescription = "Background image",
            modifier = modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(.4f))
        )
        OnboardingContent(
            title = "Experience the travel and adventure.",
            description = "The airline that connects Tanzania more to the world than any other awaits you.",
            onNextClick = onNextClick,
            modifier = modifier.align(Alignment.BottomStart),
            pagerState = pagerState
        )
        Spacer(modifier = modifier.height(64.dp))
    }
}
