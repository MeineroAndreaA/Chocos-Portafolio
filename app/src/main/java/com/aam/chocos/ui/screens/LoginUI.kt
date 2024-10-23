package com.aam.chocos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginUI(
    state: SignInState?,
    onSignInClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Chocos", modifier = Modifier.align(Alignment.CenterHorizontally))

            Button(onClick = { onSignInClick() }) {
                Text(text = "Loguearse con Google")
            }

        }
    }
}