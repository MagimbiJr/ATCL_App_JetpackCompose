package com.tana.airtanzaniaapp.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.tana.airtanzaniaapp.util.Resource

data class FlightCapacity(
    val id: String = "",
    val business: Long = 0,
    val economy: Long = 0,
    val flightId: String = ""
)

fun DocumentSnapshot.toFlightCapacity(): FlightCapacity? {
    return try {
        val business = getLong("business")!!
        val economy = getLong("economy")!!
        val flightId = getString("flightId")!!
        FlightCapacity(id = id, business = business, economy = economy, flightId = flightId)
    } catch (e: FirebaseFirestoreException) {
        Resource.Failure(message = e.localizedMessage)
        null
    }
}
