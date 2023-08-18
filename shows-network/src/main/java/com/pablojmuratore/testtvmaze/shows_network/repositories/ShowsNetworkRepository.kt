package com.pablojmuratore.testtvmaze.shows_network.repositories

import com.pablojmuratore.testtvmaze.shows_network.api.IShowsApi
import com.pablojmuratore.testtvmaze.shows_network.models.ShowDTO
import com.pablojmuratore.testtvmaze.shows_network.models.ShowInfoDTO

class ShowsNetworkRepository(
    private val showsApi: IShowsApi
) : IShowsNetworkRepository {
    override suspend fun searchShows(query: String): List<ShowInfoDTO> {
        return showsApi.searchShows(query)
    }

    override suspend fun loadShowDetail(showId: Long): ShowDTO {
        return showsApi.loadShowDetail(showId)
    }
}