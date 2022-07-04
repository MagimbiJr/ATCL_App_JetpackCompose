package com.tana.airtanzaniaapp.data

import com.google.firebase.firestore.DocumentSnapshot
import com.tana.airtanzaniaapp.util.Resource

data class Flight(
    val flightId: String = "",
    val flightName: String = "",
    val flyFrom: String = "",
    val flyTo: String = "",
    val date: String = "",
    val flightTime: String = "",
    val departureTime: String = "",
    val departureAirport: String = "",
    val arrivalTime: String = "",
    val arrivalAirport: String = "",
    val price: Long = 0
) {
    companion object {
        fun DocumentSnapshot.toFlight(): Flight? {
            return try {
                val flightName = getString("flightName")!!
                val flyFrom = getString("flyFrom")!!
                val flyTo = getString("flyTo")!!
                val date = getString("date")!!
                val flightTime = getString("flightTime")!!
                val departureTime = getString("departureTime")!!
                val departureAirport = getString("departureAirport")!!
                val arrivalTime = getString("arrivalTime")!!
                val arrivalAirport = getString("arrivalAirport")!!
                val price = getLong("price")!!
                Flight(
                    flightId = id, flightName = flightName, flyFrom = flyFrom,
                    flyTo = flyTo, date = date, flightTime = flightTime,departureTime = departureTime,
                    departureAirport = departureAirport, arrivalAirport = arrivalAirport,
                    arrivalTime = arrivalTime, price = price
                )
            } catch (e: Exception) {
                Resource.Failure(e.localizedMessage)
                null
            }
        }
    }
}
