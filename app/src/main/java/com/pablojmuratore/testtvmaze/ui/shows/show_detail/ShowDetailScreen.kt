package com.pablojmuratore.testtvmaze.ui.shows.show_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.Episode
import com.pablojmuratore.testtvmaze.model.data_states.ShowState
import com.pablojmuratore.testtvmaze.ui.components.Poster
import com.pablojmuratore.testtvmaze.ui.components.ShowEpisodesViewer
import com.pablojmuratore.testtvmaze.ui.components.ShowGenresViewer
import com.pablojmuratore.testtvmaze.ui.components.ShowScheduleDaysViewer
import com.pablojmuratore.testtvmaze.ui.episodes.EpisodeDetailScreen
import kotlinx.coroutines.launch

@Composable
fun ShowDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ShowDetailViewModel
) {
    ShowDetailScreenUI(
        modifier = modifier,
        showState = viewModel.showState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowDetailScreenUI(
    modifier: Modifier = Modifier,
    showState: ShowState = ShowState.Undefined
) {
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var selectedEpisode: Episode? by remember { mutableStateOf(null) }
    val screenScrollState = rememberScrollState()

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
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.loading_show)
                )
            }
            is ShowState.Loaded -> {
                val show = showState.show
                val showSummary = HtmlCompat.fromHtml(show.summary ?: stringResource(id = R.string.no_summary_message), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .verticalScroll(screenScrollState),

                    ) {
                    val posterUrl: String? = show.image?.medium ?: null
                    val episodes = show.embeddedData?.episodes ?: emptyList()

                    Poster(
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.poster_width))
                            .height(dimensionResource(id = R.dimen.poster_height))
                            .align(Alignment.CenterHorizontally),
                        posterUrl = posterUrl
                    )
                    Divider(modifier = Modifier.height(16.dp), color = Color.Transparent)
                    Text(
                        text = show.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.time_string,  show.schedule.time)
                        )
                        Divider(modifier = Modifier.weight(1.0f), color = Color.Transparent)
                        ShowScheduleDaysViewer(show.schedule.days)
                    }
                    ShowGenresViewer(
                        modifier = Modifier.padding(vertical = 8.dp),
                        genres = show.genres
                    )
                    Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)

                    // summary
                    Text(text = stringResource(id = R.string.summary), fontWeight = FontWeight.Bold)
                    Text(
                        text = showSummary,
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)

                    // episodes
                    Text(text = stringResource(id = R.string.episodes), fontWeight = FontWeight.Bold)
                    if (episodes.isNotEmpty()) {
                        ShowEpisodesViewer(
                            modifier = Modifier.padding(top = 8.dp),
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
