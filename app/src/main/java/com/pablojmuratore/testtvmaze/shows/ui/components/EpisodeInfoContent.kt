package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.shows.models.Episode

@Composable
fun EpisodeInfoContent(
    modifier: Modifier = Modifier,
    episode: Episode
) {
    val episodeSummary = HtmlCompat.fromHtml(episode.summary ?: stringResource(id = R.string.no_summary_message), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

    Column(
        modifier = modifier
    ) {
        Text(
            text = episode.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
        Text(text = stringResource(id = R.string.episode_number, episode.number))
        Text(text = stringResource(id = R.string.season_number, episode.season))
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
        Text(text = episodeSummary)
    }
}


// previews

@Preview(showBackground = true)
@Composable
fun PreviewEpisodeInfoContent() {
    val testEpisode = Episode(1, "episode 1", 1, 1, summary = "this is the summary for episode 1")

    EpisodeInfoContent(episode = testEpisode)
}