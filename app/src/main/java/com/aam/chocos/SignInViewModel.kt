package com.aam.chocos

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSingningResul(result: SignInResult) {
        _state.update {
            it.copy(
                signInSuccesfull = result.data != null,
                signInError = result.errorMesssage
            )
        }

    }
}