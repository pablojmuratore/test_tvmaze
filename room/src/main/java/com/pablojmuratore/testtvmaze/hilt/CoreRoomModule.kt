package com.pablojmuratore.testtvmaze.hilt

import android.content.Context
import com.pablojmuratore.testtvmaze.room.AppDatabase
import com.pablojmuratore.testtvmaze.shows.models.ILikedShowsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreRoomModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return AppDatabase.getInstance(applicationContext)
    }

    @Singleton
    @Provides
    fun provideLikedShowsDao(database: AppDatabase): ILikedShowsDao {
        return database.likedShowsDao()
    }
}