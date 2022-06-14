package com.tana.airtanzaniaapp.presentation.onboarding.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.ui.components.ATCPrimaryButton
import com.tana.airtanzaniaapp.presentation.ui.components.ATCSecondaryButton
import com.tana.airtanzaniaapp.presentation.ui.theme.*

@Composable
fun OnboardingThree(
    modifier: Modifier = Modifier,
    systemUiController: SystemUiController,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    systemUiController.setStatusBarColor(OxfordBlue)
    systemUiController.setNavigationBarColor(EerieBlack)
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.landing_bg_3),
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
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            ATCSecondaryButton(
                text = "register",
                onClick = onRegisterClick,
                modifier = modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            Spacer(modifier = modifier.height(12.dp))
            ATCPrimaryButton(
                text = "login",
                onClick = onLoginClick,
                modifier = modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }

        Spacer(modifier = modifier.height(64.dp))
    }
}