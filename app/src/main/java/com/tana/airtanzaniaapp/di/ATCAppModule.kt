package com.tana.airtanzaniaapp.di

import com.google.firebase.firestore.FirebaseFirestore
import com.tana.airtanzaniaapp.data.repository.ATCRepository
import com.tana.airtanzaniaapp.data.repository.ATCRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ATCAppModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideRepository(firebase: FirebaseFirestore): ATCRepository {
        return ATCRepositoryImpl(db = firebase)
    }
}