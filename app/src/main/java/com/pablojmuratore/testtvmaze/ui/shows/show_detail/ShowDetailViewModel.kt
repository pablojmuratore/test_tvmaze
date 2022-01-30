package com.pablojmuratore.testtvmaze.ui.shows.show_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablojmuratore.testtvmaze.model.data_states.ShowState
import com.pablojmuratore.testtvmaze.repositories.IRemoteShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel
@Inject constructor(
    private val remoteShowsRepository: IRemoteShowsRepository
) : ViewModel() {
    var showState by mutableStateOf<ShowState>(ShowState.Undefined)
        private set

    fun loadShow(showId: Long) {
        if (showState is ShowState.Undefined) {
            viewModelScope.launch {
                showState = ShowState.Loading

                val loadedShow = remoteShowsRepository.loadShowDetail(showId)

                showState = ShowState.Loaded(loadedShow)
            }
        }
    }
}