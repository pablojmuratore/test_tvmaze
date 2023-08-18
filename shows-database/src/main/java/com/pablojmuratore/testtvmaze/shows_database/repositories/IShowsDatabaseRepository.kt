package com.pablojmuratore.testtvmaze.shows_database.repositories

import com.pablojmuratore.testtvmaze.shows.models.LikedShowEntity
import kotlinx.coroutines.flow.Flow

interface IShowsDatabaseRepository {
    suspend fun getShowLiked(showId: Long): Flow<Boolean>
    suspend fun setShowLiked(showId: Long, liked: Boolean)
    fun getLikedShows(): Flow<List<LikedShowEntity>>
}