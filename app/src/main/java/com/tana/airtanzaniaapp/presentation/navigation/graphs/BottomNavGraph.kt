package com.tana.airtanzaniaapp.presentation.navigation.graphs

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.tana.airtanzaniaapp.presentation.home.HomeScreen
import com.tana.airtanzaniaapp.presentation.ui.components.ATCPrimaryButton
import com.tana.airtanzaniaapp.presentation.ui.components.ATCSecondaryButton

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
        composable(route = "profile") {
            val auth = FirebaseAuth.getInstance()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (auth.currentUser != null) {
                    Text(text = "Profile screen")
                    Spacer(modifier = Modifier.heightIn(24.dp))
                    Text(text = "Hello ${auth.currentUser?.displayName}")
                } else {
                    ATCPrimaryButton(
                        text = "login",
                        onClick = { navHostController.navigate("login") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.heightIn(12.dp))
                    ATCSecondaryButton(
                        text = "register",
                        onClick = { navHostController.navigate("register") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Logout",
                        style = MaterialTheme.typography.button,
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { auth.signOut() }
                    )
                }
            }
        }
    }
}