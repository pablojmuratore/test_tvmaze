package com.pablojmuratore.testtvmaze.hilt

import com.pablojmuratore.testtvmaze.repositories.ILocalDataRepository
import com.pablojmuratore.testtvmaze.repositories.LocalDataRepository
import com.pablojmuratore.testtvmaze.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return AppDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun provideLocalDataRepository(database: AppDatabase): ILocalDataRepository {
        return LocalDataRepository(database)
    }
}