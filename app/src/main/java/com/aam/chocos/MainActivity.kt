package com.aam.chocos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aam.chocos.ui.screens.LoginUI
import com.aam.chocos.ui.theme.PortafolioTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val googleAuthCli by lazy {
        GoogleAuth(
            context = applicationContext,
            onTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortafolioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in")
                    {
                        composable("sign_in") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state = viewModel.state.collectAsState().value
                            val laucher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult =
                                                googleAuthCli.getSignInResultFromIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                            viewModel.onSingningResul(signInResult)
                                        }
                                    }
                                })

                            LoginUI(state = state, onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthCli.signIn()
                                    laucher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}



