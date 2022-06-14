package com.tana.airtanzaniaapp.presentation.onboarding.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.onboarding.components.OnboardingContent
import com.tana.airtanzaniaapp.presentation.ui.theme.AirSuperiorityBlue
import com.tana.airtanzaniaapp.presentation.ui.theme.EerieBlack

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingOne(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    systemUiController: SystemUiController,
    pagerState: PagerState
) {
    systemUiController.setStatusBarColor(AirSuperiorityBlue.copy(.5f))
    systemUiController.setNavigationBarColor(EerieBlack)
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.landing_bg_1),
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
            title = "Explore the World with Us!",
            description = "Your talent determines what you can do. Your motivation determines how much you’re willing to do. Your attitude determines how well you do it.",
            onNextClick = onNextClick,
            modifier = modifier.align(Alignment.BottomStart),
            pagerState = pagerState
        )
        Spacer(modifier = modifier.height(64.dp))
    }
}

