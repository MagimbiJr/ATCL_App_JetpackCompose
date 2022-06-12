package com.tana.airtanzaniaapp.presentation.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.presentation.navigation.graphs.bottomNavGraph
import com.tana.airtanzaniaapp.presentation.ui.components.ATCBottomNav

@Composable
fun ATCNavGraph(
    navHostController: NavHostController,
    scrollState: ScrollState,
    systemUiController: SystemUiController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = { ATCBottomNav(navHostController = navHostController, modifier = modifier)}
    ) {
        NavHost(
            navController = navHostController,
            startDestination = "bottom_nav",
            modifier = modifier.padding(it)
        ) {
            bottomNavGraph(
                navHostController = navHostController,
                systemUiController = systemUiController,
                scrollState = scrollState
            )
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
        }
    }
}