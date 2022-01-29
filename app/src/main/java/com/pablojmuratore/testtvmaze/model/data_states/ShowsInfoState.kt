package com.pablojmuratore.testtvmaze.model.data_states

import com.pablojmuratore.testtvmaze.model.ShowInfo

sealed class ShowsInfoState {
    object Undefined : ShowsInfoState()
    object Loading : ShowsInfoState()
    data class Loaded(val showsInfo: List<ShowInfo>) : ShowsInfoState()
}