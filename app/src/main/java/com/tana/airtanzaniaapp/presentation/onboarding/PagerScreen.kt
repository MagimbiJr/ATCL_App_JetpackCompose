package com.tana.airtanzaniaapp.presentation.onboarding

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.tana.airtanzaniaapp.presentation.ui.components.ATCPrimaryButton
import com.tana.airtanzaniaapp.presentation.ui.theme.Cultured
import com.tana.airtanzaniaapp.presentation.ui.theme.GreenBlue

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun PageScreen(
    page: OnboardingPage,
    pagerState: PagerState,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = page.background),
            contentDescription = "Background image",
            modifier = modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(.7f))
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 60.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = page.title,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Cultured
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(text = page.description, color = Cultured.copy(.6f))
            Spacer(modifier = modifier.height(12.dp))
            AnimatedVisibility(
                visible = pagerState.currentPage != 2,
                exit = slideOutVertically(
                    animationSpec = tween(
                        durationMillis = 600, easing = FastOutSlowInEasing
                    )
                ) + fadeOut() + shrinkVertically()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        activeColor = GreenBlue,
                        inactiveColor = Cultured
                    )
                    Text(
                        text = "Next",
                        style = MaterialTheme.typography.button,
                        fontSize = 16.sp,
                        modifier = modifier
                            .clickable { page.onClick() },
                        color = Cultured
                    )
                }
            }
            AnimatedVisibility(
                visible = pagerState.currentPage == 2,
                enter = scaleIn(
                    tween(
                        durationMillis = 400, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(),
                exit = scaleOut(
                    tween(
                        durationMillis = 800, easing = FastOutSlowInEasing
                    )
                ) + fadeOut() + shrinkVertically()
            ) {
                ATCPrimaryButton(
                    text = "finish",
                    onClick = page.onClick,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}