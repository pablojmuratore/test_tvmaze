package com.pablojmuratore.testtvmaze.shows_network.api

import com.pablojmuratore.testtvmaze.shows_network.models.ShowDTO
import com.pablojmuratore.testtvmaze.shows_network.models.ShowInfoDTO

interface IShowsApi {
    suspend fun searchShows(query: String = ""): List<ShowInfoDTO>
    suspend fun loadShowDetail(showId: Long): ShowDTO
}