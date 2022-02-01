package com.pablojmuratore.testtvmaze.ui.shows.shows_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.Show
import com.pablojmuratore.testtvmaze.model.ShowInfo
import com.pablojmuratore.testtvmaze.model.data_states.ShowsInfoState
import com.pablojmuratore.testtvmaze.ui.components.SearchBar
import com.pablojmuratore.testtvmaze.ui.components.ShowListItem

@Composable
fun ShowsListScreen(
    modifier: Modifier = Modifier,
    viewModel: ShowsListViewModel,
    onShowInfoClicked: (showInfo: ShowInfo) -> Unit
) {
    ShowsListScreenUI(
        modifier = modifier,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onSearchQuerySearchClicked = viewModel::onSearchQuerySearchClicked,
        showsInfoState = viewModel.showsInfoState,
        likedShows = viewModel.likedShows,
        onShowInfoClicked = onShowInfoClicked,
        onShowLiked = viewModel::onShowLiked
    )
}

@OptIn(ExperimentalAnimationApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showListScreenUIPreview() {
    ShowsListScreenUI()
}