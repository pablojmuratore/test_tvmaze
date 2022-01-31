package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.model.Episode

@Composable
fun EpisodeListItem(
    episode: Episode,
    onEpisodeClicked: (episode: Episode) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(vertical = 4.dp)
            .clickable {
                onEpisodeClicked(episode)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(24.dp),
            text = "${episode.number}",
            textAlign = TextAlign.Right
        )
        Divider(modifier = Modifier.width(8.dp))
        Text(
            text = episode.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun EpisodeListItemPreview() {
    val mockEpisode = Episode(1, "Episode 1", 3, 5, summary = "This is the summary for the mock episode.")

    EpisodeListItem(episode = mockEpisode)
}