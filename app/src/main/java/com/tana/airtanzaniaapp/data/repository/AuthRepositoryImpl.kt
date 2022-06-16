package com.tana.airtanzaniaapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tana.airtanzaniaapp.data.Response
import com.tana.airtanzaniaapp.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : AuthRepository {
    override fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        profilePicture: String
    ): Flow<Resource<Response>> = callbackFlow {
        val registeredUser = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "profilePicture" to profilePicture
        )

        if (email.isNotBlank() && password.isNotBlank()) {
            val resultSnapshot = auth.createUserWithEmailAndPassword(
                email, password
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = task.result.user
                    val uid = currentUser?.uid
                    if (uid != null) {
                        db.collection("users").document(uid).set(registeredUser)
                            .addOnCompleteListener { dbTask ->
                                val response = if (dbTask.isSuccessful) {
                                    val data = Response(
                                        success = true,
                                        message = "User created successful"
                                    )
                                    Resource.Success(data = data)
                                } else {
                                    Resource.Failure(message = dbTask.exception?.localizedMessage)
                                }
                                trySend(response)
                            }
                    }
                } else {
                    Resource.Failure(message = task.exception?.localizedMessage)
                }
            }

            awaitClose { resultSnapshot.isCanceled }
        }
    }

    override fun login(
        email: String,
        password: String
    ): Flow<Resource<Response>> = callbackFlow {
        if (email.isNotBlank() && password.isNotBlank()) {

            val resultSnapshot =
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    val response = if (task.isSuccessful) {
                        val data = Response(success = true, message = "Sign in successful")
                        Resource.Success(data = data)
                    } else {
                        Resource.Failure(message = task.exception?.localizedMessage)
                    }
                    trySend(response)
                }

            awaitClose { resultSnapshot.isCanceled }
        }

    }

}