package com.pablojmuratore.testtvmaze.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablojmuratore.testtvmaze.TestTvMazeApp
import com.pablojmuratore.testtvmaze.model.data_states.LoginState
import com.pablojmuratore.testtvmaze.model.data_states.PinCheckState
import com.pablojmuratore.testtvmaze.repositories.LocalDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    val localDataRepository: LocalDataRepository
) : ViewModel() {
    var loginState: LoginState by mutableStateOf(LoginState.Main)
        private set

    var pin by mutableStateOf("")
        private set

    var isPinConfigured by mutableStateOf(false)
        private set

    var pinCheckState: PinCheckState by mutableStateOf(PinCheckState.Unchecked)
        private set

    init {
        getLoginState()
    }

    private fun getLoginState() {
        viewModelScope.launch {
            localDataRepository.getLoginPin(TestTvMazeApp.getContext()).collectLatest { pin ->
                isPinConfigured = pin.isNotEmpty()
            }
        }
    }

    fun onPinClicked() {
        loginState = if (!isPinConfigured) {
            LoginState.ConfigurePin
        } else {
            LoginState.UsePin
        }
    }

    fun onFingerprintClicked() {

    }

    fun onPinChanged(pin: String) {
        this.pin = pin
    }

    fun onSetPin(pin: String) {
        viewModelScope.launch {
            localDataRepository.setLoginPin(pin, TestTvMazeApp.getContext())
        }
    }

    fun checkPin(pin: String) {
        viewModelScope.launch {
            localDataRepository.getLoginPin(TestTvMazeApp.getContext()).collectLatest { storedPin ->
                pinCheckState = PinCheckState.Checked(storedPin == pin)
            }
        }
    }
}