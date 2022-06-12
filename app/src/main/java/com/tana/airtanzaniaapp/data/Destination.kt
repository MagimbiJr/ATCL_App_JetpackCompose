package com.tana.airtanzaniaapp.data

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.DocumentSnapshot
import com.tana.airtanzaniaapp.util.Resource

data class Destination(
    val id: String = "",
    val name: String = "",
    val travelDays: String = "",
    val price: Long = 0,
    val image: String = ""
) {

    companion object {
        fun DocumentSnapshot.toDestination(): Destination? {
            return try {
                val name = getString("name")!!
                val travelDays = getString("travelDays")!!
                val price = getLong("price")!!
                val image = getString("image")!!

                Destination(id = id, name = name, travelDays = travelDays, price = price, image = image)
            } catch (e: Exception) {
                Resource.Failure(e.localizedMessage)
                null
            }
        }
    }
}
