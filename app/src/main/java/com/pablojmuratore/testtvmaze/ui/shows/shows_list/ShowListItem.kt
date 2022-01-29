package com.pablojmuratore.testtvmaze.ui.shows.shows_list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.pablojmuratore.testtvmaze.model.ShowInfo

@Composable
fun ShowListItem(
    showInfo: ShowInfo
) {
    Text(text = showInfo.show.name)
}