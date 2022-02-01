package com.pablojmuratore.testtvmaze.ui.shows.shows_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pablojmuratore.testtvmaze.model.LikedShow
import com.pablojmuratore.testtvmaze.model.Show
import com.pablojmuratore.testtvmaze.model.data_states.ShowsInfoState
import com.pablojmuratore.testtvmaze.repositories.ILocalDataRepository
import com.pablojmuratore.testtvmaze.repositories.IRemoteShowsRepository
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
    var searchQuery by mutableStateOf("")
        private set

    var showsInfoState by mutableStateOf<ShowsInfoState>(ShowsInfoState.Undefined)
        private set

    //    var likedShows: MutableStateFlow<LikedShowsStateFlow> = MutableStateFlow(LikedShowsStateFlow.Success(emptyList()))
    var likedShows by mutableStateOf<LikedShowsStateFlow>(LikedShowsStateFlow.Undefined)
        private set

    init {
        initLikedShowsList()
        searchShows("")
    }

    private fun initLikedShowsList() {
        viewModelScope.launch {
            localDataRepository.getLikedShows().collectLatest { loadedLikedShows ->
                likedShows = LikedShowsStateFlow.Loaded(loadedLikedShows)
            }
        }
    }

    private fun searchShows(query: String = "") {
        viewModelScope.launch {
            showsInfoState = ShowsInfoState.Loading

            val showsInfo = remoteShowsRepository.searchShows(query)

            showsInfoState = ShowsInfoState.Loaded(showsInfo)
        }
    }

    fun onSearchQueryChanged(searchQuery: String) {
        this.searchQuery = searchQuery
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