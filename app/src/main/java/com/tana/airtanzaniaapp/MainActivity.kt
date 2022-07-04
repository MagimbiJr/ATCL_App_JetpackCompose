package com.tana.airtanzaniaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tana.airtanzaniaapp.presentation.SplashViewModel
import com.tana.airtanzaniaapp.presentation.authentication.login.LoginScreen
import com.tana.airtanzaniaapp.presentation.authentication.register.RegisterScreen
import com.tana.airtanzaniaapp.presentation.home.HomeScreen
import com.tana.airtanzaniaapp.presentation.navigation.ATCNavGraph
import com.tana.airtanzaniaapp.presentation.ui.theme.AirTanzaniaAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //installSplashScreen()

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }


        setContent {
            val navHostController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val scrollState = rememberScrollState()
            val coroutineScope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            val focusManager = LocalFocusManager.current
            val screen by splashViewModel.startDestination
            AirTanzaniaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ATCNavGraph(
                        navHostController = navHostController,
                        startDestination = screen,
                        scrollState = scrollState,
                        systemUiController = systemUiController,
                        coroutineScope = coroutineScope,
                        scaffoldState = scaffoldState,
                        focusManager = focusManager
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AirTanzaniaAppTheme {
        Greeting("Android")
    }
}