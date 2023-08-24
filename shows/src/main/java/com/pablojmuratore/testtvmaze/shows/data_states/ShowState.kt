package com.pablojmuratore.testtvmaze.shows.data_states

import com.pablojmuratore.testtvmaze.shows.models.Show

sealed class ShowState {
    object Undefined : ShowState()
    object Loading : ShowState()
    data class Loaded(val show: Show) : ShowState()
}