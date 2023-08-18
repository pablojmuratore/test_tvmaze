package com.pablojmuratore.testtvmaze.shows.models

import androidx.room.Dao
import androidx.room.Query
import com.pablojmuratore.testtvmaze.core_room.models.IDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ILikedShowsDao : IDao<LikedShowEntity> {
    @Query("select * from liked_shows where show_id=:showId limit 1")
    fun getById(showId: Long): Flow<LikedShowEntity?>

    @Query("select * from liked_shows")
    fun getAll(): Flow<List<LikedShowEntity>>

    @Query("delete from liked_shows")
    suspend fun deleteAll()
}
