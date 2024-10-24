package com.aam.chocos.data

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

class Repository @Inject constructor(
    private val credentialManager: CredentialManager,
    private val googleRequest: GetCredentialRequest
) {
    private suspend fun googleSignIn(context: Context): Flow<Result<AuthResult>> {
        val firebaseAuth = FirebaseAuth.getInstance()
        return callbackFlow {
            try {
                // Get the credential result
                val result: GetCredentialResponse =
                    credentialManager.getCredential(context, googleRequest)
                val credential = result.credential

                // Check if the received credential is a valid Google ID Token
                if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)
                    val authCredential =
                        GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                    val authResult = firebaseAuth.signInWithCredential(authCredential).await()
                    trySend(Result.success(authResult))
                } else {
                    throw RuntimeException("Received an invalid credential type")
                }
            } catch (e: GetCredentialCancellationException) {
                trySend(Result.failure(Exception("Sign-in was canceled. Please try again.")))

            } catch (e: Exception) {
                trySend(Result.failure(e))
            }
            awaitClose { }
        }
    }
}