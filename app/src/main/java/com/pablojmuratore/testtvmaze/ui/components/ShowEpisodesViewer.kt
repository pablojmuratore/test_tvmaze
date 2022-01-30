package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pablojmuratore.testtvmaze.model.Episode

@Composable
fun ShowEpisodesViewer(
    episodes: List<Episode> = emptyList()
) {
    val seasons = episodes.sortedBy { it.number }.sortedBy { it.season }.groupBy { it.season }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // There is sticky headers but still experimental
        seasons.forEach { season ->
            item {
                SeasonListItem(season = season.key)
            }
            items(season.value) { episode ->
                EpisodeListItem(episode = episode)
            }
        }
    }
}