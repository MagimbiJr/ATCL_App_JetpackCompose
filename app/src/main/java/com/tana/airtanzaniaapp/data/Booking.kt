package com.tana.airtanzaniaapp.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.tana.airtanzaniaapp.util.Resource

data class Booking(
    val id: String = "",
    val flyFrom: String = "",
    val flyTo: String = "",
    val departDate: String = "",
    val departTime: String = "",
    val ticketAmount: Long = 0,
    val seats: Long = 0,
    val classType: String = "",
    val flightId: String = "",
    val userId: String = ""
) {
    companion object {
        fun DocumentSnapshot.toBooking(): Booking? {
            return try {
                val departDestination = getString("departString")!!
                val arrivalDestination = getString("arrivalDestination")!!
                val departDate = getString("departDate")!!
                val departTime = getString("departTime")!!
                val ticketAmount = getLong("ticketAmount")!!
                val seats = getLong("seat")!!
                val classType = getString("classType")!!
                val flightId = getString("flightId")!!
                val userId = getString("userId")!!
                Booking(
                    id = id, flyFrom = departDestination,
                    flyTo = arrivalDestination,
                    departDate = departDate, departTime = departTime, ticketAmount = ticketAmount,
                    seats = seats, classType = classType, flightId = flightId, userId = userId
                )
            } catch (e: FirebaseFirestoreException) {
                Resource.Failure(message = e.localizedMessage)
                null
            }
        }
    }
}
