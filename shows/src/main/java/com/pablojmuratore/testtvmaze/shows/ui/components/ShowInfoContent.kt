package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.pablojmuratore.testtvmaze.shows.R
import com.pablojmuratore.testtvmaze.shows.models.Show

@Composable
fun ShowInfoContent(show: Show) {
    val showSummary = HtmlCompat.fromHtml(show.summary ?: stringResource(id = R.string.no_summary_message), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

    Column {
        Text(
            text = show.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (show.schedule.time.isNotBlank()) {
                Text(
                    text = stringResource(id = R.string.time_string, show.schedule.time)
                )
            }
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
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
        )
    }
}