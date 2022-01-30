package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pablojmuratore.testtvmaze.R

@Composable
fun SeasonListItem(
    season: Int
) {
    Text(text = stringResource(id = R.string.season_number, season))
}