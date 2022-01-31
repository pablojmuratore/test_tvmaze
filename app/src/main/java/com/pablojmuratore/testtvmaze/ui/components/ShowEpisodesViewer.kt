package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pablojmuratore.testtvmaze.model.Episode

@Composable
fun ShowEpisodesViewer(
    modifier: Modifier = Modifier,
    episodes: List<Episode> = emptyList(),
    onEpisodeClicked: (episode: Episode) -> Unit = {}
) {
    val seasons = episodes.sortedBy { it.number }.sortedBy { it.season }.groupBy { it.season }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        seasons.forEach { season ->
            SeasonListItem(season = season.key)

            season.value.forEach { episode ->
                EpisodeListItem(
                    episode = episode,
                    onEpisodeClicked = onEpisodeClicked
                )
            }
        }
    }
}