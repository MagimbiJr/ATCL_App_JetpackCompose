package com.tana.airtanzaniaapp.presentation.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tana.airtanzaniaapp.R
import com.tana.airtanzaniaapp.presentation.navigation.screens.BottomNavScreens

@Composable
fun ATCBottomNav(
    navHostController: NavHostController,
    modifier: Modifier
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomNavItems = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Explore,
        BottomNavScreens.Trip
    )
    val backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface
    else MaterialTheme.colors.background

    BottomNavigation(
        backgroundColor = backgroundColor,
    ) {
        bottomNavItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navHostController.navigate(item.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "Navigation item icon",
                        modifier = modifier
                            .size(24.dp)
                    )
                },
                label = {
                    Text(text = item.title)
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onSurface.copy(.4f),
            )
        }
    }
}