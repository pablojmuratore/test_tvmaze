package com.pablojmuratore.testtvmaze.di

import com.pablojmuratore.testtvmaze.network.ITvMazeApi
import com.pablojmuratore.testtvmaze.repositories.IRemoteShowsRepository
import com.pablojmuratore.testtvmaze.repositories.RemoteShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoriesModule {

    @ViewModelScoped
    @Provides
    fun provideRemoteShowsRepository(tvMazeApi: ITvMazeApi): IRemoteShowsRepository {
        return RemoteShowsRepository(tvMazeApi)
    }
}