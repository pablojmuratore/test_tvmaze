package com.pablojmuratore.testtvmaze.shows.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo
import com.pablojmuratore.testtvmaze.shows.ui.screens.ShowsListScreen
import com.pablojmuratore.testtvmaze.shows.viewmodels.ShowsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.showsListComposable(
    onShowClicked: (showInfo: ShowInfo) -> Unit = {}
) {
    composable(route = ShowsNavigation.Screens.ShowsListScreen.route) {
        val viewModel = hiltViewModel<ShowsListViewModel>()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) }
                )
            }
        ) { innerPadding ->
            ShowsListScreen(
                modifier = Modifier.padding(innerPadding),
                searchQuery = viewModel.searchQuery,
                onSearchQueryChanged = viewModel::onSearchQueryChanged,
                onSearchQuerySearchClicked = viewModel::onSearchQuerySearchClicked,
                showsInfoState = viewModel.showsInfoState,
                likedShows = viewModel.likedShows,
                onShowInfoClicked = onShowClicked,
                onShowLiked = viewModel::onShowLiked
            )
        }
    }
}