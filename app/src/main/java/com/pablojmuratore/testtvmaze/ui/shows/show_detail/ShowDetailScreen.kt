package com.pablojmuratore.testtvmaze.ui.shows.show_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.Episode
import com.pablojmuratore.testtvmaze.model.ShowScheduleDaysViewer
import com.pablojmuratore.testtvmaze.model.data_states.ShowState
import com.pablojmuratore.testtvmaze.ui.components.Poster
import com.pablojmuratore.testtvmaze.ui.components.ShowEpisodesViewer
import com.pablojmuratore.testtvmaze.ui.components.ShowGenresViewer
import com.pablojmuratore.testtvmaze.ui.episodes.EpisodeDetailScreen
import kotlinx.coroutines.launch

@Composable
fun ShowDetailScreen(
    navController: NavHostController,
    viewModel: ShowDetailViewModel
) {
    ShowDetailScreenUI(
        showState = viewModel.showState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowDetailScreenUI(
    showState: ShowState = ShowState.Undefined
) {
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var selectedEpisode: Episode? by remember { mutableStateOf(null) }

    if (!bottomSheetState.isVisible) {
        selectedEpisode = null
    }

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = bottomSheetState,
        sheetContent = {
            if (selectedEpisode != null) {
                EpisodeDetailScreen(selectedEpisode as Episode)
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = stringResource(id = R.string.no_episode_detail)
                )
            }
        }
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

                    Poster(
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.poster_width))
                            .height(dimensionResource(id = R.dimen.poster_height)),
                        posterUrl = posterUrl
                    )
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
                        ShowEpisodesViewer(
                            episodes,
                            onEpisodeClicked = { episode ->
                                scope.launch {
                                    selectedEpisode = episode
                                    bottomSheetState.show()
                                }
                            })
                    } else {
                        Text(text = stringResource(id = R.string.no_episodes_message))
                    }
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
