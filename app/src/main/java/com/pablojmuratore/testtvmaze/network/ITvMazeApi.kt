package com.pablojmuratore.testtvmaze.network

import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo

interface ITvMazeApi {
    suspend fun searchShows(query: String = ""): List<ShowInfo>
    suspend fun loadShowDetail(showId: Long): Show
}