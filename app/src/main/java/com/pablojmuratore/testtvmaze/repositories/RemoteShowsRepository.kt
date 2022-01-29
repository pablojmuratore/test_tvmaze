package com.pablojmuratore.testtvmaze.repositories

import com.pablojmuratore.testtvmaze.model.ShowInfo
import com.pablojmuratore.testtvmaze.network.ITvMazeApi

class RemoteShowsRepository(
    private val tvMazeApi: ITvMazeApi
) : IRemoteShowsRepository {
    override suspend fun searchShows(query: String): List<ShowInfo> {
        return tvMazeApi.searchShows(query)
    }
}