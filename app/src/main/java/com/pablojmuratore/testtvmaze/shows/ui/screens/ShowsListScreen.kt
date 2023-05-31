package com.pablojmuratore.testtvmaze.shows.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.shows.data_states.ShowsInfoState
import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo
import com.pablojmuratore.testtvmaze.shows.ui.components.ShowListItem
import com.pablojmuratore.testtvmaze.shows.viewmodels.ShowsListViewModel
import com.pablojmuratore.testtvmaze.ui.components.SearchBar

@Composable
fun ShowsListScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onSearchQueryChanged: (searchQuery: String) -> Unit = {},
    onSearchQuerySearchClicked: (searchQuery: String) -> Unit = {},
    showsInfoState: ShowsInfoState = ShowsInfoState.Undefined,
    likedShows: ShowsListViewModel.LikedShowsStateFlow = ShowsListViewModel.LikedShowsStateFlow.Undefined,
    onShowLiked: (show: Show, liked: Boolean) -> Unit = { show: Show, liked: Boolean -> },
    onShowInfoClicked: (showInfo: ShowInfo) -> Unit = {}
) {
    ShowsListScreenUI(
        modifier = modifier,
        searchQuery = searchQuery,
        onSearchQueryChanged = onSearchQueryChanged,
        onSearchQuerySearchClicked = onSearchQuerySearchClicked,
        showsInfoState = showsInfoState,
        likedShows = likedShows,
        onShowInfoClicked = onShowInfoClicked,
        onShowLiked = onShowLiked
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowsListScreenUI(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onSearchQueryChanged: (searchQuery: String) -> Unit = {},
    onSearchQuerySearchClicked: (searchQuery: String) -> Unit = {},
    showsInfoState: ShowsInfoState = ShowsInfoState.Undefined,
    likedShows: ShowsListViewModel.LikedShowsStateFlow = ShowsListViewModel.LikedShowsStateFlow.Undefined,
    onShowInfoClicked: (showInfo: ShowInfo) -> Unit = {},
    onShowLiked: (show: Show, liked: Boolean) -> Unit = { show: Show, liked: Boolean -> }
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
    ) {
        val likedShowIds: List<Long> = when (likedShows) {
            is ShowsListViewModel.LikedShowsStateFlow.Loaded -> {
                likedShows.likedShows.map { it.showId }
            }
            else -> {
                emptyList()
            }
        }

        SearchBar(
            value = searchQuery,
            onQueryChanged = onSearchQueryChanged,
            onSearchClicked = {
                keyboardController?.hide()
                onSearchQuerySearchClicked(searchQuery)
            },
        )

        when (showsInfoState) {
            is ShowsInfoState.Loading -> {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.loading_shows)
                )
            }

            is ShowsInfoState.Loaded -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    items(showsInfoState.showsInfo) { showInfo ->
                        val isShowLiked = showInfo.show.id in likedShowIds

                        ShowListItem(
                            showInfo = showInfo,
                            isShowLiked = isShowLiked,
                            onShowInfoClicked = onShowInfoClicked,
                            onShowLiked = onShowLiked
                        )
                    }
                }
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowListScreenUIPreview() {
    ShowsListScreenUI()
}