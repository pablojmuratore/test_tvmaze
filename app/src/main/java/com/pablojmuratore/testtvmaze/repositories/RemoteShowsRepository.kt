package com.pablojmuratore.testtvmaze.repositories

import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo
import com.pablojmuratore.testtvmaze.network.ITvMazeApi

class RemoteShowsRepository(
    private val tvMazeApi: ITvMazeApi
) : IRemoteShowsRepository {
    override suspend fun searchShows(query: String): List<ShowInfo> {
        return tvMazeApi.searchShows(query)
    }

    override suspend fun loadShowDetail(showId: Long): Show {
        return tvMazeApi.loadShowDetail(showId)
    }
}