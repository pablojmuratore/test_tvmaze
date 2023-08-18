package com.pablojmuratore.testtvmaze.shows.repositories

import com.pablojmuratore.testtvmaze.shows.models.LikedShow
import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo
import kotlinx.coroutines.flow.Flow

interface IShowsRepository {
    suspend fun searchShows(query: String = ""): List<ShowInfo>
    suspend fun loadShowDetail(showId: Long): Show

    suspend fun getShowLiked(showId: Long): Flow<Boolean>
    suspend fun setShowLiked(showId: Long, liked: Boolean)
    fun getLikedShows(): Flow<List<LikedShow>>
}