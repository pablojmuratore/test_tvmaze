package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.Show
import com.pablojmuratore.testtvmaze.model.ShowInfo

@Composable
fun ShowListItem(
    showInfo: ShowInfo,
    isShowLiked: Boolean,
    onShowInfoClicked: (showInfo: ShowInfo) -> Unit = {},
    onShowLiked: (show: Show, liked: Boolean) -> Unit = { show: Show, liked: Boolean -> }
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
            val likedIconColor = if (isShowLiked) colorResource(id = R.color.like_icon_selected) else colorResource(id = R.color.like_icon_unselected)

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
                Icon(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(16.dp)
                        .clickable {
                            onShowLiked(showInfo.show, !isShowLiked)
                        },
                    tint = likedIconColor,
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null
                )
            }
        }
    }
}