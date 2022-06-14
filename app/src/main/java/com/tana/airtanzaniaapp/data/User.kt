package com.tana.airtanzaniaapp.data

import com.google.firebase.firestore.DocumentSnapshot

data class User(
    val userId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val profileImg: String = "",
)

fun DocumentSnapshot.toUser(): User? {
    return try {
        val firstName = getString("firstName")!!
        val lastName = getString("lastName")!!
        val email = getString("email")!!
        val profileImg = getString("profileImg")!!
        User(
            userId = id, firstName = firstName, lastName = lastName,
            email = email, profileImg = profileImg
        )
    } catch (e: Exception) {
        null
    }
}
