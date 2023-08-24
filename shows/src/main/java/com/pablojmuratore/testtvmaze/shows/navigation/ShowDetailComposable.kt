package com.pablojmuratore.testtvmaze.shows.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pablojmuratore.testtvmaze.shows.ui.screens.ShowDetailScreen
import com.pablojmuratore.testtvmaze.shows.viewmodels.ShowDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.showDetailComposable(
    navController: NavHostController
) {
    composable(
        route = ShowsNavigation.Screens.ShowDetailScreen.route,
        arguments = listOf(
            navArgument(ShowsNavigation.Screens.PARAM_ID) {
                type = NavType.LongType
            }
        )
    ) {
        val showId = it.arguments?.getLong(ShowsNavigation.Screens.PARAM_ID) ?: 0L
        val viewModel = hiltViewModel<ShowDetailViewModel>()

        if (showId > 0) {
            viewModel.loadShow(showId)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "show") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            ShowDetailScreen(
                modifier = Modifier.padding(innerPadding),
                showState = viewModel.showState,
                isShowLiked = viewModel.isShowLiked,
                onShowLiked = viewModel::onShowLiked
            )
        }
    }
}