package com.pablojmuratore.testtvmaze.di

import com.pablojmuratore.testtvmaze.database.AppDatabase
import com.pablojmuratore.testtvmaze.repositories.ILocalDataRepository
import com.pablojmuratore.testtvmaze.repositories.LocalDataRepository
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