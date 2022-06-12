package com.tana.airtanzaniaapp.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.tana.airtanzaniaapp.data.Booking
import com.tana.airtanzaniaapp.data.Destination
import com.tana.airtanzaniaapp.data.Destination.Companion.toDestination
import com.tana.airtanzaniaapp.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ATCRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : ATCRepository {


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun bestTrips(): Flow<Resource<List<Destination>>> = callbackFlow {

        val trips = db.collection("best trips")
        val snapShotListener = trips.addSnapshotListener { value, error ->
            val response = if (value != null) {
                val data = value.documents.mapNotNull { it.toDestination() }
                Resource.Success(data = data)

            } else {
                Resource.Failure(message = error?.localizedMessage)
            }
            trySend(response)
        }

        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun bookFlight(): Flow<Resource<Booking>> = callbackFlow {

    }

}