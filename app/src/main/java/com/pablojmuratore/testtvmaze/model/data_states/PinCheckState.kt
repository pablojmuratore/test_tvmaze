package com.pablojmuratore.testtvmaze.model.data_states

sealed class PinCheckState {
    object Unchecked : PinCheckState()
    data class Checked(val isCorrect: Boolean) : PinCheckState()
}