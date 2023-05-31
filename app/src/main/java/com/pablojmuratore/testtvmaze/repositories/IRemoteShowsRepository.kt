package com.pablojmuratore.testtvmaze.repositories

import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo

interface IRemoteShowsRepository {
    suspend fun searchShows(query: String = ""): List<ShowInfo>

    suspend fun loadShowDetail(showId: Long): Show
}