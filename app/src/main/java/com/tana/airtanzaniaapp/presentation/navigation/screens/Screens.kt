package com.tana.airtanzaniaapp.presentation.navigation.screens

import com.tana.airtanzaniaapp.R

sealed class BottomNavScreens(val route: String, val title: String, val icon: Int) {
    object Home : BottomNavScreens(route = "home", title = "Home", icon = R.drawable.home_icon)
    object Explore : BottomNavScreens(route = "explore", title = "Explore", icon = R.drawable.compass_icon)
    object Trip : BottomNavScreens(route = "trip", title = "Trip", icon = R.drawable.shopping_bag_icon)
    object Profile : BottomNavScreens(route = "profile", title = "Profile", R.drawable.person_icon)
}

sealed class OnBoardingScreen(val route: String) {
    object OnBoardingOne : OnBoardingScreen("onboarding_one")
    object OnBoardingTwo : OnBoardingScreen("onboarding_two")
    object OnBoardingThree : OnBoardingScreen("onboarding_three")
}
