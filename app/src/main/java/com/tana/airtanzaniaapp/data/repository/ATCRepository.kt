package com.tana.airtanzaniaapp.data.repository

import com.tana.airtanzaniaapp.data.Booking
import com.tana.airtanzaniaapp.data.Destination
import com.tana.airtanzaniaapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ATCRepository {

    fun bestTrips(): Flow<Resource<List<Destination>>>

    fun bookFlight(): Flow<Resource<Booking>>

}