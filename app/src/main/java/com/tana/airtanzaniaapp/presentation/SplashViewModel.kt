package com.tana.airtanzaniaapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.tana.airtanzaniaapp.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val currentUser: State<FirebaseUser?> = mutableStateOf(null)
    private val _startDestination: MutableState<String> = mutableStateOf("onboarding")
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            preferencesManager.readOnboardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = "bottom_nav"
                } else {
                    _startDestination.value = "onboarding"
                }
            }
            _isLoading.value = false
        }
    }
}