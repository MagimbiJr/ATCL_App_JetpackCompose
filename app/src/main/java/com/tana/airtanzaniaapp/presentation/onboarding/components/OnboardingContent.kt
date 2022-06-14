package com.tana.airtanzaniaapp.presentation.onboarding.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.tana.airtanzaniaapp.presentation.ui.theme.GrayX11Gray
import com.tana.airtanzaniaapp.presentation.ui.theme.GreenBlue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingContent(
    title: String,
    description: String,
    onNextClick: () -> Unit,
    modifier: Modifier,
    pagerState: PagerState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = modifier.height(20.dp))
        Text(
            text = description,
            color = Color.White.copy(.7f)
        )
        Spacer(modifier = modifier.height(24.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
           HorizontalPagerIndicator(
               pagerState = pagerState,
               activeColor = GreenBlue,
               inactiveColor = GrayX11Gray
           )
            Text(
                text = "Next",
                fontWeight = FontWeight.SemiBold,
                modifier = modifier
                    .clickable { onNextClick() },
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}