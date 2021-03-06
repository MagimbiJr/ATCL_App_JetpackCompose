package com.tana.airtanzaniaapp.presentation.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.tana.airtanzaniaapp.presentation.navigation.graphs.authGraph
import com.tana.airtanzaniaapp.presentation.navigation.graphs.bottomNavGraph
import com.tana.airtanzaniaapp.presentation.onboarding.OnBoardingScreen
import com.tana.airtanzaniaapp.presentation.ui.components.ATCBottomNav
import com.tana.airtanzaniaapp.presentation.ui.components.ATCPrimaryButton
import com.tana.airtanzaniaapp.presentation.ui.components.ATCSecondaryButton
import kotlinx.coroutines.CoroutineScope

@Composable
fun ATCNavGraph(
    navHostController: NavHostController,
    startDestination: String,
    scrollState: ScrollState,
    focusManager: FocusManager,
    scaffoldState: ScaffoldState,
    systemUiController: SystemUiController,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            if (startDestination == "bottom_nav") {
                ATCBottomNav(navHostController = navHostController, modifier = modifier)
            }
        }
    ) {
        NavHost(
            navController = navHostController,
            startDestination = startDestination,
            modifier = modifier.padding(it)
        ) {
            bottomNavGraph(
                navHostController = navHostController,
                systemUiController = systemUiController,
                scrollState = scrollState
            )
            composable("onboarding") {
                OnBoardingScreen(
                    navHostController = navHostController,
                    systemUiController = systemUiController,
                    coroutineScope = coroutineScope
                )
            }
            composable(route = "trip_detail") {
                Text(text = "Trip Details")
            }
            composable(route = "book_flight_screen") {
                Text(text = "Book Flight")
            }
            composable(route = "rent_car_screen") {
                Text(text = "Rent Car")
            }
            composable(route = "hotels_screen") {
                Text(text = "Hotels ")
            }
            composable(route = "trip_list") {
                Text(text = "All trips")
            }
            authGraph(
                navHostController = navHostController,
                systemUiController = systemUiController,
                focusManager = focusManager,
                scrollState = scrollState,
                scaffoldState = scaffoldState
            )
            composable(route = "privacy_policy") {
                Text(text = "Privacy Policy")
            }
        }
    }
}