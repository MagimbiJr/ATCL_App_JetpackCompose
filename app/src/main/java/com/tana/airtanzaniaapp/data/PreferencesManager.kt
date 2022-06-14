package com.tana.airtanzaniaapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.tana.airtanzaniaapp.util.ATCConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException


val Context.datastore: DataStore<Preferences> by preferencesDataStore("atc_preferences")

class PreferencesManager(context: Context) {

    val dataStore = context.datastore

    val onboardingStateKey = booleanPreferencesKey(ATCConstants.ON_BOARDING_STATE_KEY)
    suspend fun saveOnboardingState(completed: Boolean) {
        dataStore.edit { preference ->
            preference[onboardingStateKey] = completed
        }
    }

    fun readOnboardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is java.io.IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[onboardingStateKey] ?: false
                onBoardingState
            }
    }

}