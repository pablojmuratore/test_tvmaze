package com.pablojmuratore.testtvmaze.shows_network.repositories

import com.pablojmuratore.testtvmaze.shows_network.models.ShowDTO
import com.pablojmuratore.testtvmaze.shows_network.models.ShowInfoDTO

interface IShowsNetworkRepository {
    suspend fun searchShows(query: String = ""): List<ShowInfoDTO>
    suspend fun loadShowDetail(showId: Long): ShowDTO
}