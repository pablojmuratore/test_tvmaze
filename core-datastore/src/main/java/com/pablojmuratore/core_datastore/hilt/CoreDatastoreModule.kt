package com.pablojmuratore.core_datastore.hilt

import android.content.Context
import com.pablojmuratore.core_datastore.TvMazeDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreDatastoreModule {
    @Singleton
    @Provides
    fun provideTvMazeDataStore(@ApplicationContext applicationContext: Context): TvMazeDataStore {
        return TvMazeDataStore(applicationContext)
    }
}