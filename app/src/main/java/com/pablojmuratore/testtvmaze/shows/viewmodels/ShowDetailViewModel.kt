package com.pablojmuratore.testtvmaze.shows.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablojmuratore.testtvmaze.repositories.ILocalDataRepository
import com.pablojmuratore.testtvmaze.repositories.IRemoteShowsRepository
import com.pablojmuratore.testtvmaze.shows.data_states.ShowState
import com.pablojmuratore.testtvmaze.shows.models.Show
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
    private var _showState = mutableStateOf<ShowState>(ShowState.Undefined)
    val showState by _showState

    private var _isShowLiked = mutableStateOf<Boolean>(false)
    val isShowLiked by _isShowLiked

    fun loadShow(showId: Long) {
        if (_showState.value is ShowState.Undefined) {
            viewModelScope.launch {
                _showState.value = ShowState.Loading

                try {
                    val loadedShow = remoteShowsRepository.loadShowDetail(showId)

                    _showState.value = if (loadedShow != null) {
                        ShowState.Loaded(loadedShow)
                    } else {
                        ShowState.Undefined
                    }

                    localDataRepository.getShowLiked(showId).collectLatest { liked ->
                        _isShowLiked.value = liked
                    }
                } catch (e: Exception) {
                    Log.d("---x", "loadShow ( ShowDetailViewModel ) - ERROR: ${e.message}")
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