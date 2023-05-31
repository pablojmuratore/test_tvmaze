package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.shows.models.Episode

@Composable
fun EpisodesContent(
    episodes: List<Episode>,
    onEpisodeClicked: (episode: Episode) -> Unit = {}
) {
    Column {
        Text(text = stringResource(id = R.string.episodes), fontWeight = FontWeight.Bold)

        if (episodes.isNotEmpty()) {
            ShowEpisodesViewer(
                modifier = Modifier.padding(top = 8.dp),
                episodes,
                onEpisodeClicked = { episode ->
                    onEpisodeClicked(episode)
                })
        } else {
            Text(text = stringResource(id = R.string.no_episodes_message))
        }
    }
}


// previews

@Preview(showBackground = true)
@Composable
fun PreviewEpisodesContent() {
    val testEpisode1 = Episode(1, "episode 1", 1, 1, summary = "summary of episode 1")
    val testEpisode2 = Episode(2, "episode 2", 1, 2, summary = "summary of episode 2")
    val testEpisodes = listOf(testEpisode1, testEpisode2)

    EpisodesContent(episodes = testEpisodes)
}