package com.aam.chocos

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class GoogleAuth(private val context: Context, private val onTapClient: SignInClient) {

    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try {
            onTapClient.beginSignIn(provideBeginSignInBuilder()).await()

        } catch (e: Exception) {
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun getSignInResultFromIntent(intent: Intent): SignInResult {
        val credentials = onTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credentials.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(data = user, errorMesssage = null)
        } catch (e: Exception) {
            SignInResult(data = null, errorMesssage = e.message)
        }

    }

    private fun provideBeginSignInBuilder(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.google_client))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}