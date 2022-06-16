package com.tana.airtanzaniaapp.presentation.navigation.graphs

import androidx.compose.foundation.ScrollState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.ui.focus.FocusManager
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.airtanzaniaapp.presentation.authentication.login.LoginScreen
import com.tana.airtanzaniaapp.presentation.authentication.register.RegisterScreen

fun NavGraphBuilder.authGraph(
    navHostController: NavHostController,
    systemUiController: SystemUiController,
    scaffoldState: ScaffoldState,
    focusManager: FocusManager,
    scrollState: ScrollState
) {
    navigation(startDestination = "login", route = "auth_graph") {
        composable(route = "login") {
            LoginScreen(
                onNavigate = {
                    navHostController.navigate(it.route) {
                        launchSingleTop = true
                    }
                },
                systemUiController = systemUiController,
                scaffoldState = scaffoldState,
                focusManager = focusManager,
                scrollState = scrollState
            )
        }
        composable(route = "register") {
            RegisterScreen(
                onNavigate = { navHostController.navigate(it.route) },
                systemUiController = systemUiController,
                focusManager = focusManager,
                scaffoldState = scaffoldState,
                scrollState = scrollState
            )
        }
        composable(route = "recover_password") {
            Text(text = "Recover Password")
        }
    }
}
