package com.tana.airtanzaniaapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toUiString(): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM".take(3) +" dd, yyyy")
    return formatter.format(this)
}