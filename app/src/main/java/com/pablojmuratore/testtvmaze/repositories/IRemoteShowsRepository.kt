package com.pablojmuratore.testtvmaze.repositories

import com.pablojmuratore.testtvmaze.model.Show
import com.pablojmuratore.testtvmaze.model.ShowInfo

interface IRemoteShowsRepository {
    suspend fun searchShows(query: String = ""): List<ShowInfo>

    suspend fun loadShowDetail(showId: Long): Show
}