package com.pablojmuratore.testtvmaze.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pablojmuratore.testtvmaze.shows.models.ILikedShowsDao
import com.pablojmuratore.testtvmaze.shows.models.LikedShowEntity

@Database(entities = [LikedShowEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(applicationContext: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "TestTvMazeDb"
                ).build()

                instance!!
            }
        }
    }

    abstract fun likedShowsDao(): ILikedShowsDao
}