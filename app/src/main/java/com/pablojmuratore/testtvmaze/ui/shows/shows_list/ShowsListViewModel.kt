package com.pablojmuratore.testtvmaze.ui.shows.shows_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablojmuratore.testtvmaze.model.data_states.ShowsInfoState
import com.pablojmuratore.testtvmaze.repositories.IRemoteShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsListViewModel
@Inject constructor(
    private val remoteShowsRepository: IRemoteShowsRepository
) : ViewModel() {
    var searchQuery by mutableStateOf<String>("")
        private set

    var showsInfoState by mutableStateOf<ShowsInfoState>(ShowsInfoState.Undefined)
        private set

    private fun searchShows(query: String = "") {
        viewModelScope.launch {
            showsInfoState = ShowsInfoState.Loading

            val showsInfo = remoteShowsRepository.searchShows(query)

            Log.d("---x", "loaded: ${showsInfo.size}")

            showsInfoState = ShowsInfoState.Loaded(showsInfo)
        }
    }

    fun onSearchQueryChanged(searchQuery: String) {
        this.searchQuery = searchQuery
    }

    fun onSearchQuerySearchClicked(searchQuery: String) {
        Log.d("---x", "onSearchQuerySearchClicked ( ShowsListViewModel ) - searchQuery: $searchQuery")
        searchShows(searchQuery)
    }
}