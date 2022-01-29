package com.pablojmuratore.testtvmaze.network

import com.pablojmuratore.testtvmaze.model.ShowInfo

interface ITvMazeApi {
    suspend fun searchShows(query: String = ""): List<ShowInfo>
}