package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.shows.models.Episode

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
            .padding(start = 16.dp)
            .clickable {
                onEpisodeClicked(episode)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(24.dp),
            text = "${episode.number}"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = episode.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EpisodeListItemPreview() {
    val testEpisode = Episode(1, "Episode 1", 3, 5, summary = "This is the summary for the mock episode.")

    EpisodeListItem(episode = testEpisode)
}