package com.pablojmuratore.testtvmaze.ui.shows.shows_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.data_states.ShowsInfoState
import com.pablojmuratore.testtvmaze.ui.components.search_bar.SearchBar

@Composable
fun ShowsListScreen(
    navController: NavHostController,
    viewModel: ShowsListViewModel
) {
    ShowsListScreenUI(
        searchQuery = viewModel.searchQuery,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onSearchQuerySearchClicked = viewModel::onSearchQuerySearchClicked,
        showsInfoState = viewModel.showsInfoState
    )
}

@Composable
fun ShowsListScreenUI(
    searchQuery: String = "",
    onSearchQueryChanged: (searchQuery: String) -> Unit = {},
    onSearchQuerySearchClicked: (searchQuery: String) -> Unit = {},
    showsInfoState: ShowsInfoState = ShowsInfoState.Undefined
) {
    Column() {
        SearchBar(
            value = searchQuery,
            onQueryChanged = onSearchQueryChanged,
            onSearchClicked = onSearchQuerySearchClicked,
        )

        when (showsInfoState) {
            is ShowsInfoState.Loading -> {
                Text(text = stringResource(id = R.string.loading))
            }
            is ShowsInfoState.Loaded -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    items(showsInfoState.showsInfo) { showInfo ->
                        ShowListItem(showInfo)
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