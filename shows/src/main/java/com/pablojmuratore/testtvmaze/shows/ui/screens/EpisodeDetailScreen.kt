package com.pablojmuratore.testtvmaze.shows.ui.screens

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.shows.models.Episode
import com.pablojmuratore.testtvmaze.shows.ui.components.EpisodeInfoContent
import com.pablojmuratore.testtvmaze.shows.ui.components.PosterContent

@Composable
fun EpisodeDetailScreen(
    episode: Episode
) {
    val posterUrl: String? = episode.image?.medium

    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp,
                        vertical = 16.dp
                    )
            ) {
                PosterContent(posterUrl = posterUrl)
                EpisodeInfoContent(episode = episode)
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                PosterContent(posterUrl = posterUrl)
                Divider(modifier = Modifier.width(16.dp), color = Color.Transparent)
                EpisodeInfoContent(
                    modifier = Modifier.weight(1.0f),
                    episode = episode
                )
            }
        }
    }
}


// previews

@Preview(showBackground = true)
@Composable
fun PreviewEpisodeDetailScreen() {
    val testEpisode = Episode(1, "episode 1", 1, 1, summary = "episode 1 summary")
    EpisodeDetailScreen(episode = testEpisode)
}