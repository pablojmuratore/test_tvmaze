package com.pablojmuratore.testtvmaze.login.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablojmuratore.testtvmaze.TestTvMazeApp
import com.pablojmuratore.testtvmaze.login.data_states.LoginState
import com.pablojmuratore.testtvmaze.login.data_states.PinCheckState
import com.pablojmuratore.testtvmaze.repositories.LocalDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel
@Inject constructor(
    private val localDataRepository: LocalDataRepository
) : ViewModel() {
    private var _loginState = mutableStateOf<LoginState>(LoginState.Main)
    val loginState by _loginState

    private var _pin = mutableStateOf<String>("")
    val pin by _pin

    private var _isPinConfigured = mutableStateOf<Boolean>(false)
    val isPinConfigured by _isPinConfigured

    private var _pinCheckState = mutableStateOf<PinCheckState>(PinCheckState.Unchecked)
    val pinCheckState by _pinCheckState

    init {
        getLoginState()
    }

    private fun getLoginState() {
        viewModelScope.launch {
            localDataRepository.getLoginPin(TestTvMazeApp.getContext()).collectLatest { pin ->
                _isPinConfigured.value = pin.isNotEmpty()
            }
        }
    }

    fun onPinClicked() {
        _loginState.value = if (!_isPinConfigured.value) {
            LoginState.ConfigurePin
        } else {
            LoginState.UsePin
        }
    }

    fun onFingerprintClicked() {

    }

    fun onPinChanged(pin: String) {
        _pin.value = pin
    }

    fun onSetPin(pin: String) {
        viewModelScope.launch {
            localDataRepository.setLoginPin(pin, TestTvMazeApp.getContext())
        }
    }

    fun checkPin(pin: String) {
        viewModelScope.launch {
            localDataRepository.getLoginPin(TestTvMazeApp.getContext()).collectLatest { storedPin ->
                _pinCheckState.value = PinCheckState.Checked(storedPin == pin)
            }
        }
    }
}