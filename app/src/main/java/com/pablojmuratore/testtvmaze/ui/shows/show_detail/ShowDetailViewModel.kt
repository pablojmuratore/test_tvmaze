package com.pablojmuratore.testtvmaze.ui.shows.show_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablojmuratore.testtvmaze.model.Show
import com.pablojmuratore.testtvmaze.model.data_states.ShowState
import com.pablojmuratore.testtvmaze.repositories.ILocalDataRepository
import com.pablojmuratore.testtvmaze.repositories.IRemoteShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel
@Inject constructor(
    private val remoteShowsRepository: IRemoteShowsRepository,
    private val localDataRepository: ILocalDataRepository
) : ViewModel() {
    var showState by mutableStateOf<ShowState>(ShowState.Undefined)
        private set

    var isShowLiked by mutableStateOf(false)
        private set

    fun loadShow(showId: Long) {
        if (showState is ShowState.Undefined) {
            viewModelScope.launch {
                showState = ShowState.Loading

                val loadedShow = remoteShowsRepository.loadShowDetail(showId)

                showState = if (loadedShow != null) {
                    ShowState.Loaded(loadedShow)
                } else {
                    ShowState.Undefined
                }
            }

            viewModelScope.launch {
                localDataRepository.getShowLiked(showId).collectLatest { liked ->
                    isShowLiked = liked
                }
            }
        }
    }

    fun onShowLiked(show: Show, liked: Boolean) {
        viewModelScope.launch {
            localDataRepository.setShowLiked(show.id, liked)
        }
    }
}