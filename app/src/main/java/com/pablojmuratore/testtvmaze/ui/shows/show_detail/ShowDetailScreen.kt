package com.pablojmuratore.testtvmaze.ui.shows.show_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.ShowScheduleDaysViewer
import com.pablojmuratore.testtvmaze.model.data_states.ShowState
import com.pablojmuratore.testtvmaze.ui.components.ShowEpisodesViewer
import com.pablojmuratore.testtvmaze.ui.components.ShowGenresViewer
import com.pablojmuratore.testtvmaze.ui.components.ShowPoster

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
            Text(text = "loading")
        }
        is ShowState.Loaded -> {
            val show = showState.show
            val showSummary = show.summary ?: stringResource(id = R.string.no_summary_message)

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val posterUrl: String? = show.image?.medium ?: null
                val episodes = show.embeddedData?.episodes ?: emptyList()

                ShowPoster(posterUrl)
                Divider(modifier = Modifier.height(16.dp))
                Text(text = show.name)
                Divider(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = show.schedule.time)
                    Divider(modifier = Modifier.weight(1.0f))
                    ShowScheduleDaysViewer(show.schedule.days)
                }
                ShowGenresViewer(show.genres)
                Divider(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.summary))
                Text(text = showSummary)
                Divider(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.episodes))
                if (episodes.isNotEmpty()) {
                    ShowEpisodesViewer(episodes)
                } else {
                    Text(text = stringResource(id = R.string.no_episodes_message))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowDetailScreenUIPreview(
) {
    ShowDetailScreenUI()
}
