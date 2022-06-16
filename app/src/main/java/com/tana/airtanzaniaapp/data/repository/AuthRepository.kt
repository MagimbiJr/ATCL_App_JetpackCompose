package com.tana.airtanzaniaapp.data.repository

import com.tana.airtanzaniaapp.data.Response
import com.tana.airtanzaniaapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        profilePicture: String = ""
    ): Flow<Resource<Response>>

    fun login(
        email: String,
        password: String
    ): Flow<Resource<Response>>
}