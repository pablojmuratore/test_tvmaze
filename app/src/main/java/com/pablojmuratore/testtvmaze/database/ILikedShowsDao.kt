package com.pablojmuratore.testtvmaze.database

import androidx.room.Dao
import androidx.room.Query
import com.pablojmuratore.testtvmaze.model.LikedShow
import kotlinx.coroutines.flow.Flow

@Dao
interface ILikedShowsDao : IDao<LikedShow> {
    @Query("select * from liked_shows where show_id=:showId limit 1")
    fun getById(showId: Long): Flow<LikedShow?>

    @Query("select * from liked_shows")
    fun getAll(): Flow<List<LikedShow>>

    @Query("delete from liked_shows")
    suspend fun deleteAll()
}
