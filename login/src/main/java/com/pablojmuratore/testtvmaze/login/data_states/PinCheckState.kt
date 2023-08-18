package com.pablojmuratore.testtvmaze.login.data_states

sealed class PinCheckState {
    object Unchecked : PinCheckState()
    data class Checked(val isCorrect: Boolean) : PinCheckState()
}