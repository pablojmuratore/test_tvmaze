package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pablojmuratore.testtvmaze.shows.models.Episode

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


// previews
@Preview(showBackground = true)
@Composable
fun PreviewShowEpisodesViewer() {
    val testEpisode1 = Episode(1, "episode 1", 1, 1, summary = "summary of episode 1")
    val testEpisode2 = Episode(2, "episode 2", 1, 2, summary = "summary of episode 2")
    val testEpisodes = listOf(testEpisode1, testEpisode2)

    ShowEpisodesViewer(episodes = testEpisodes)
}