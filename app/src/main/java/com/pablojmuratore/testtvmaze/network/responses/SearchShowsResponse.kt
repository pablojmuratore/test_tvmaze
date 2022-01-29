package com.pablojmuratore.testtvmaze.network.responses

import androidx.annotation.Keep
import com.pablojmuratore.testtvmaze.model.ShowInfo

@Keep
class SearchShowsResponse(
    val shows: List<ShowInfo>
)
