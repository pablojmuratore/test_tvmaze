package com.pablojmuratore.testtvmaze.ui.shows.show_detail

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.pablojmuratore.testtvmaze.model.data_states.ShowState

@Composable
fun ShowDetailScreen(
    navController: NavHostController,
    viewModel: ShowDetailViewModel
) {
    ShowDetailScreenUI(
        showState = viewModel.showState
    )
}

@Composable
fun ShowDetailScreenUI(
    showState: ShowState = ShowState.Undefined
) {
    when (showState) {
        is ShowState.Loading -> {
            Text(text = "show detail")
        }
        is ShowState.Loaded -> {
            Text(text = "show: ${showState.show.name}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowDetailScreenUIPreview(
) {
    ShowDetailScreenUI()
}
