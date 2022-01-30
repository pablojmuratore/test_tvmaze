package com.pablojmuratore.testtvmaze.network

import com.pablojmuratore.testtvmaze.model.Show
import com.pablojmuratore.testtvmaze.model.ShowInfo

interface ITvMazeApi {
    suspend fun searchShows(query: String = ""): List<ShowInfo>
    suspend fun loadShowDetail(showId: Long): Show
}