package com.pablojmuratore.testtvmaze.shows.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.shows.R
import com.pablojmuratore.testtvmaze.shows.data_states.ShowState
import com.pablojmuratore.testtvmaze.shows.models.Episode
import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows.models.ShowSchedule
import com.pablojmuratore.testtvmaze.shows.ui.components.EpisodesContent
import com.pablojmuratore.testtvmaze.shows.ui.components.PosterContent
import com.pablojmuratore.testtvmaze.shows.ui.components.ShowInfoContent
import kotlinx.coroutines.launch

@Composable
fun ShowDetailScreen(
    modifier: Modifier = Modifier,
    showState: ShowState,
    isShowLiked: Boolean = false,
    onShowLiked: (show: Show, liked: Boolean) -> Unit = { show: Show, liked: Boolean -> }
) {
    ShowDetailScreenUI(
        modifier = modifier,
        showState = showState,
        isShowLiked = isShowLiked,
        onShowLiked = onShowLiked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailScreenUI(
    modifier: Modifier = Modifier,
    showState: ShowState = ShowState.Undefined,
    isShowLiked: Boolean = false,
    onShowLiked: (show: Show, liked: Boolean) -> Unit = { show: Show, liked: Boolean -> }
) {
    val scope = rememberCoroutineScope()
    var selectedEpisode: Episode? by remember { mutableStateOf(null) }
    val screenScrollState = rememberScrollState()

    var openBottomSheet by remember { mutableStateOf(false) }
    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    if (!bottomSheetState.isVisible) {
        selectedEpisode = null
    }

    if (openBottomSheet) {
        ModalBottomSheet(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetState = bottomSheetState,
            onDismissRequest = {
                openBottomSheet = false
            }
        ) {
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
    }

    when (showState) {
        is ShowState.Loading -> {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.loading_show)
            )
        }

        is ShowState.Loaded -> {
            val show = showState.show
            val posterUrl: String? = show.image?.medium
            val episodes = show.embeddedData?.episodes ?: emptyList()
            val likedIconColor = if (isShowLiked) colorResource(id = R.color.like_icon_selected) else colorResource(id = R.color.like_icon_unselected)

            BoxWithConstraints() {
                if (maxWidth < 600.dp) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .verticalScroll(screenScrollState)
                    ) {
                        Row {
                            PosterContent(posterUrl = posterUrl)
                            Divider(modifier = Modifier.weight(1.0f), color = Color.Transparent)
                            IconButton(
                                modifier = Modifier.size(56.dp),
                                onClick = {
                                    onShowLiked(show, !isShowLiked)
                                }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(56.dp)
                                        .padding(16.dp),
                                    imageVector = Icons.Filled.Favorite,
                                    tint = likedIconColor,
                                    contentDescription = null
                                )
                            }
                        }
                        Divider(modifier = Modifier.height(16.dp), color = Color.Transparent)
                        ShowInfoContent(show = show)
                        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)

                        // episodes
                        EpisodesContent(
                            episodes = episodes,
                            onEpisodeClicked = { episode ->
                                scope.launch {
                                    selectedEpisode = episode
                                    bottomSheetState.show()
                                }
                            }
                        )
                    }
                } else {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .verticalScroll(screenScrollState)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            PosterContent(
                                modifier = Modifier
                                    .width(dimensionResource(id = R.dimen.poster_width))
                                    .height(dimensionResource(id = R.dimen.poster_height)),
                                posterUrl = posterUrl
                            )
                            Divider(modifier = Modifier.width(16.dp), color = Color.Transparent)
                            Column {
                                Row {
                                    Divider(modifier = Modifier.weight(1.0f), color = Color.Transparent)
                                    Icon(
                                        modifier = Modifier
                                            .size(56.dp)
                                            .padding(16.dp)
                                            .clickable {
                                                onShowLiked(show, !isShowLiked)
                                            },
                                        imageVector = Icons.Filled.Favorite,
                                        tint = likedIconColor,
                                        contentDescription = null
                                    )
                                }
                                ShowInfoContent(show = show)
                            }
                        }
                        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)

                        // episodes
                        EpisodesContent(
                            episodes = episodes,
                            onEpisodeClicked = { episode ->
                                scope.launch {
                                    selectedEpisode = episode
                                    bottomSheetState.show()
                                }
                            }
                        )
                    }
                }
            }
        }

        else -> {}
    }
}

// previews

@Preview(showBackground = true)
@Composable
fun ShowDetailScreenUIPreview(
) {
    val testShow = Show(1, "show 1", schedule = ShowSchedule())
    val testShowState = ShowState.Loaded(testShow)

    ShowDetailScreenUI(showState = testShowState)
}
