package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.ui.theme.TestTvMazeTheme

@Composable
fun SeasonListItem(
    season: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(
                color = if (!isSystemInDarkTheme()) {
                    colorResource(id = R.color.season_list_item_background)
                } else {
                    colorResource(id = R.color.season_list_item_background_dark)
                },
                shape = RoundedCornerShape(size = 8.dp)
            )
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = stringResource(id = R.string.season_number, season),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview()
@Composable
fun SeasonListItemPreviewLight() {
    TestTvMazeTheme(darkTheme = false) {
        SeasonListItem(season = 1)
    }
}

@Preview()
@Composable
fun SeasonListItemPreviewDark() {
    TestTvMazeTheme(darkTheme = true) {
        SeasonListItem(season = 1)
    }
}