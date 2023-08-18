package com.pablojmuratore.testtvmaze.shows_network.hilt

import com.pablojmuratore.testtvmaze.shows_network.api.IShowsApi
import com.pablojmuratore.testtvmaze.shows_network.api.ShowsApi
import com.pablojmuratore.testtvmaze.shows_network.repositories.IShowsNetworkRepository
import com.pablojmuratore.testtvmaze.shows_network.repositories.ShowsNetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @Singleton
    @Provides
    fun provideShowsApi(): IShowsApi {
        return ShowsApi
    }

    @Singleton
    @Provides
    fun provideShowsNetworkRepository(showsApi: IShowsApi): IShowsNetworkRepository {
        return ShowsNetworkRepository(showsApi)
    }
}