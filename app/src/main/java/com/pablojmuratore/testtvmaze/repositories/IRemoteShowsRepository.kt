package com.pablojmuratore.testtvmaze.repositories

import com.pablojmuratore.testtvmaze.model.ShowInfo

interface IRemoteShowsRepository {
    suspend fun searchShows(query: String = ""): List<ShowInfo>
}