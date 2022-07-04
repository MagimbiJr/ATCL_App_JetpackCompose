package com.tana.airtanzaniaapp.data

import com.google.firebase.firestore.DocumentSnapshot
import com.tana.airtanzaniaapp.util.Resource

data class Booking(
    val id: String = "",
    val flyFrom: String = "",
    val flyTo: String = "",
    val departDate: String = "",
    val ticketAmount: Long = 0,
    val classType: String = "",
    val flightId: String = ""
) {
    companion object {
        fun DocumentSnapshot.toBooking(): Booking? {
            return try {
                val departDestination = getString("departString")!!
                val arrivalDestination = getString("arrivalDestination")!!
                val departDate = getString("departDate")!!
                val ticketAmount = getLong("ticketAmount")!!
                val classType = getString("classType")!!
                val flightId = getString("flightId")!!
                Booking(
                    id = id, flyFrom = departDestination,
                    flyTo = arrivalDestination,
                    departDate = departDate, ticketAmount = ticketAmount,
                    classType = classType, flightId = flightId
                )
            } catch (e: Exception) {
                Resource.Failure(message = e.localizedMessage)
                null
            }
        }
    }
}
