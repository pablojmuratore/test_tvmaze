package com.pablojmuratore.testtvmaze.shows.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablojmuratore.testtvmaze.repositories.ILocalDataRepository
import com.pablojmuratore.testtvmaze.repositories.IRemoteShowsRepository
import com.pablojmuratore.testtvmaze.shows.data_states.ShowsInfoState
import com.pablojmuratore.testtvmaze.shows.models.LikedShow
import com.pablojmuratore.testtvmaze.shows.models.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsListViewModel
@Inject constructor(
    private val remoteShowsRepository: IRemoteShowsRepository,
    private val localDataRepository: ILocalDataRepository
) : ViewModel() {
    private var _searchQuery = mutableStateOf<String>("")
    val searchQuery by _searchQuery

    private var _showsInfoState = mutableStateOf<ShowsInfoState>(ShowsInfoState.Undefined)
    val showsInfoState by _showsInfoState

    private var _likedShows = mutableStateOf<LikedShowsStateFlow>(LikedShowsStateFlow.Undefined)
    val likedShows by _likedShows

    init {
        initLikedShowsList()
        searchShows("")
    }

    private fun initLikedShowsList() {
        viewModelScope.launch {
            localDataRepository.getLikedShows().collectLatest { loadedLikedShows ->
                _likedShows.value = LikedShowsStateFlow.Loaded(loadedLikedShows)
            }
        }
    }

    private fun searchShows(query: String = "") {
        viewModelScope.launch {
            _showsInfoState.value = ShowsInfoState.Loading

            val showsInfo = remoteShowsRepository.searchShows(query)

            _showsInfoState.value = ShowsInfoState.Loaded(showsInfo)
        }
    }

    fun onSearchQueryChanged(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    fun onSearchQuerySearchClicked(searchQuery: String) {
        searchShows(searchQuery)
    }

    fun onShowLiked(show: Show, liked: Boolean) {
        viewModelScope.launch {
            localDataRepository.setShowLiked(show.id, liked)
        }
    }

    sealed class LikedShowsStateFlow {
        object Undefined : LikedShowsStateFlow()
        object Loading : LikedShowsStateFlow()
        data class Loaded(val likedShows: List<LikedShow>) : LikedShowsStateFlow()
    }
}