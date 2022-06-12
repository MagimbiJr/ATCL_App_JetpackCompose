package com.tana.airtanzaniaapp.util

sealed class ATCAppEvents {
    data class Navigate(val route: String) : ATCAppEvents()
    object PopBack : ATCAppEvents()
    data class ShowSnackBar(val message: String) : ATCAppEvents()
}
