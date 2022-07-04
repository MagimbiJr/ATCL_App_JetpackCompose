package com.tana.airtanzaniaapp.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.tana.airtanzaniaapp.data.Booking
import com.tana.airtanzaniaapp.data.Flight
import com.tana.airtanzaniaapp.data.Flight.Companion.toFlight
import com.tana.airtanzaniaapp.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : BookingRepository {

    override fun searchFlight(from: String, to: String, date: String): Flow<Resource<List<Flight>>> = callbackFlow {
        val flights = db.collection("flights").whereEqualTo("flyFrom", from)
            .whereEqualTo("flyTo", to).whereEqualTo("date", date)
        val snapShotListener = flights.addSnapshotListener { value, error ->
            val response = if (value != null) {
                val data = value.documents.mapNotNull { it.toFlight() }
                Resource.Success(data = data)
            } else {

                Log.d("TAG", "searchFlight: ${error?.localizedMessage}")
                Resource.Failure(message = error?.localizedMessage)
            }
            trySend(response)
        }

        awaitClose { snapShotListener.remove() }
    }


    override fun booking(): Flow<Resource<Booking>> = callbackFlow {

    }

}