package com.pablojmuratore.testtvmaze.model.data_states

sealed class LoginState {
    object Main : LoginState()
    object ConfigurePin : LoginState()
    object UsePin : LoginState()
    object WrongPin : LoginState()
}