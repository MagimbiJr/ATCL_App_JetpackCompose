package com.tana.airtanzaniaapp.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.tana.airtanzaniaapp.data.Booking
import com.tana.airtanzaniaapp.data.Destination
import com.tana.airtanzaniaapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ATCRepository {

    fun bestTrips(): Flow<Resource<List<Destination>>>

    fun bookFlight(): Flow<Resource<Booking>>

}