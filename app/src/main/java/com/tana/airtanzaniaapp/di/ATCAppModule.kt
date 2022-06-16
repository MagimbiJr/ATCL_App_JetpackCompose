package com.tana.airtanzaniaapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tana.airtanzaniaapp.data.PreferencesManager
import com.tana.airtanzaniaapp.data.repository.ATCRepository
import com.tana.airtanzaniaapp.data.repository.ATCRepositoryImpl
import com.tana.airtanzaniaapp.data.repository.AuthRepository
import com.tana.airtanzaniaapp.data.repository.AuthRepositoryImpl
import com.tana.airtanzaniaapp.domain.auth_use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideRepository(firebase: FirebaseFirestore): ATCRepository {
        return ATCRepositoryImpl(db = firebase)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(db: FirebaseFirestore, auth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(db = db, auth = auth)

    @Provides
    @Singleton
    fun providePreferencesManager(
        @ApplicationContext context: Context
    ) : PreferencesManager = PreferencesManager(context = context)

    @Provides
    @Singleton
    fun provideValidateFirstNameUseCase(): ValidateFirstNameUseCase =
        ValidateFirstNameUseCase()

    @Provides
    @Singleton
    fun provideValidateLastNameUseCase(): ValidateLastNameUseCase =
        ValidateLastNameUseCase()

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(): ValidateEmailUseCase =
        ValidateEmailUseCase()

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): ValidatePasswordUseCase =
        ValidatePasswordUseCase()

    @Provides
    @Singleton
    fun provideValidateVerifyPasswordUseCase(): ValidateVerifyPasswordUseCase =
        ValidateVerifyPasswordUseCase()

    @Provides
    @Singleton
    fun provideValidateTermsCheckUseCase(): ValidateTermsCheckUseCase =
        ValidateTermsCheckUseCase()
}