package com.pablojmuratore.testtvmaze.shows_database.hilt

import com.pablojmuratore.testtvmaze.shows.models.ILikedShowsDao
import com.pablojmuratore.testtvmaze.shows_database.repositories.IShowsDatabaseRepository
import com.pablojmuratore.testtvmaze.shows_database.repositories.ShowsDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShowsDatabaseModule {
    @Singleton
    @Provides
    fun provideShowsDatabaseRepository(likedShowsDao: ILikedShowsDao): IShowsDatabaseRepository {
        return ShowsDatabaseRepository(likedShowsDao)
    }
}