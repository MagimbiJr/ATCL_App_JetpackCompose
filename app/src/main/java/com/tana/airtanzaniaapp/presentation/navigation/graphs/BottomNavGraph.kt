package com.tana.airtanzaniaapp.presentation.navigation.graphs

import androidx.compose.foundation.ScrollState
import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.presentation.home.HomeScreen

fun NavGraphBuilder.bottomNavGraph(
    navHostController: NavHostController,
    scrollState: ScrollState,
    systemUiController: SystemUiController
) {
    navigation(startDestination = "home", route = "bottom_nav") {
        composable(route = "home") {
            HomeScreen(
                scrollState = scrollState,
                systemUiController = systemUiController,
                onNavigate = { navHostController.navigate(it.route) }
            )
        }
        composable(route = "explore") {
            Text(text = "Explore")
        }
        composable(route = "trip") {
            Text(text = "Trip")
        }
    }
}