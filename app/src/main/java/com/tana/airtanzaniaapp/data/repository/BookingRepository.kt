package com.tana.airtanzaniaapp.data.repository

import com.tana.airtanzaniaapp.data.Booking
import com.tana.airtanzaniaapp.data.Destination
import com.tana.airtanzaniaapp.data.Flight
import com.tana.airtanzaniaapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface BookingRepository {
    fun searchFlight(
        from: String,
        to: String,
        date: String,
    ): Flow<Resource<List<Flight>>>

    fun booking(): Flow<Resource<Booking>>
}