package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.ShowInfo
import com.pablojmuratore.testtvmaze.ui.components.Poster

@Composable
fun ShowListItem(
    showInfo: ShowInfo,
    onShowInfoClicked: (showInfo: ShowInfo) -> Unit = {}
) {
    Surface(
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .height(96.dp)
                .clickable {
                    onShowInfoClicked(showInfo)
                }
                .padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                val posterUrl: String? = showInfo.show.image?.medium ?: null
                Poster(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.poster_list_item_width))
                        .height(dimensionResource(id = R.dimen.poster_list_item_height)),
                    posterUrl = posterUrl
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(1.0f),
                    text = showInfo.show.name
                )
            }
        }
    }
}