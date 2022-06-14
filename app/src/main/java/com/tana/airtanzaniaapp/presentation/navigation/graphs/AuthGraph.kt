package com.tana.airtanzaniaapp.presentation.navigation.graphs

import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authGraph(
    navHostController: NavHostController,
) {
    navigation(startDestination = "login", route = "auth_graph") {
        composable(route = "login") {
            Text(text = "Login")
        }
        composable(route = "register") {
            Text(text = "Register")
        }
    }
}
