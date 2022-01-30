package com.pablojmuratore.testtvmaze.model.data_states

import com.pablojmuratore.testtvmaze.model.Show

sealed class ShowState {
    object Undefined : ShowState()
    object Loading : ShowState()
    data class Loaded(val show: Show) : ShowState()
}