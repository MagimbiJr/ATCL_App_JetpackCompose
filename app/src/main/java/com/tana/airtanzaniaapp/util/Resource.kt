package com.tana.airtanzaniaapp.util

import com.google.firebase.firestore.QuerySnapshot

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T?) : Resource<T>(data = data)
    class Failure(message: String?) : Resource<Nothing>(message = message)
    object Loading : Resource<Nothing>()
}