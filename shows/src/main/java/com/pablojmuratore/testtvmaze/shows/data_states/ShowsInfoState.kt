package com.pablojmuratore.testtvmaze.shows.data_states

import com.pablojmuratore.testtvmaze.shows.models.ShowInfo

sealed class ShowsInfoState {
    object Undefined : ShowsInfoState()
    object Loading : ShowsInfoState()
    data class Loaded(val showsInfo: List<ShowInfo>) : ShowsInfoState()
}