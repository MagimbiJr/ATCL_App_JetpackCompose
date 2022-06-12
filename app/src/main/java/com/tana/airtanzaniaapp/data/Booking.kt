package com.tana.airtanzaniaapp.data

import com.google.firebase.firestore.DocumentSnapshot
import com.tana.airtanzaniaapp.util.Resource
import java.sql.Timestamp
import java.util.*

data class Booking(
    val id: String = UUID.randomUUID().toString(),
    val departDestination: String = "",
    val arrivalDestination: String = "",
    val departDate: String = "",
    val ticketAmount: Long = 0,
    val classType: String = ""
) {
    companion object {
        fun DocumentSnapshot.toBooking(): Booking? {
            return try {
                val departDestination = getString("departString")!!
                val arrivalDestination = getString("arrivalDestination")!!
                val departDate = getString("departDate")!!
                val ticketAmount = getLong("ticketAmount")!!
                val classType = getString("classType")!!
                Booking(
                    id = id, departDestination = departDestination,
                    arrivalDestination = arrivalDestination,
                    departDate = departDate, ticketAmount = ticketAmount,
                    classType = classType
                )
            } catch (e: Exception) {
                Resource.Failure(message = e.localizedMessage)
                null
            }
        }
    }
}
