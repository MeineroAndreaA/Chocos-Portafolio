package com.aam.chocos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aam.chocos.viewmodel.SignInViewModel
import com.aam.chocos.ui.screens.LoginUI
import com.aam.chocos.ui.theme.PortafolioTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortafolioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in")
                    {
                        composable("sign_in") {
                            val viewModel = viewModel<SignInViewModel>()
                            LoginUI(onSignInClick = {

                            })
                        }
                    }
                }
            }
        }
    }
}



