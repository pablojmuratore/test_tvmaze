package com.pablojmuratore.testtvmaze.hilt

import com.pablojmuratore.testtvmaze.network.ITvMazeApi
import com.pablojmuratore.testtvmaze.network.TvMazeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideTvMazeApi(): ITvMazeApi {
        return TvMazeApi
    }
}