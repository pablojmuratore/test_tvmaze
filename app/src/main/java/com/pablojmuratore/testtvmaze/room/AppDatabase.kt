package com.pablojmuratore.testtvmaze.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pablojmuratore.testtvmaze.TestTvMazeApp
import com.pablojmuratore.testtvmaze.shows.models.LikedShow
import com.pablojmuratore.testtvmaze.shows.room.ILikedShowsDao

@Database(entities = [LikedShow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            return instance ?: synchronized(this) {
                instance = Room.databaseBuilder(
                    TestTvMazeApp.getContext(),
                    AppDatabase::class.java,
                    "TestTvMazeDb"
                ).build()

                instance!!
            }
        }
    }

    abstract fun likedShowsDao(): ILikedShowsDao
}