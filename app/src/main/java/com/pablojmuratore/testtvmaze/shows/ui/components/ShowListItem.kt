package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo

@Composable
fun ShowListItem(
    showInfo: ShowInfo,
    isShowLiked: Boolean,
    onShowInfoClicked: (showInfo: ShowInfo) -> Unit = {},
    onShowLiked: (show: Show, liked: Boolean) -> Unit = { show: Show, liked: Boolean -> }
) {
    Surface(
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
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
                val posterUrl: String? = showInfo.show.image?.medium
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
                IconButton(
                    modifier = Modifier.size(56.dp),
                    onClick = {
                        onShowLiked(showInfo.show, !isShowLiked)
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(56.dp)
                            .padding(16.dp),
                        tint = likedIconColor,
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null
                    )

                }
            }
        }
    }
}