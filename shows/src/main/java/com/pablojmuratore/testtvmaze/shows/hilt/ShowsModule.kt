package com.pablojmuratore.testtvmaze.shows.hilt

import com.pablojmuratore.testtvmaze.shows.mappers.EpisodeDtoMapper
import com.pablojmuratore.testtvmaze.shows.mappers.LikedShowEntityMapper
import com.pablojmuratore.testtvmaze.shows.mappers.PosterImageDtoMapper
import com.pablojmuratore.testtvmaze.shows.mappers.ShowDtoMapper
import com.pablojmuratore.testtvmaze.shows.mappers.ShowEmbeddedDataDtoMapper
import com.pablojmuratore.testtvmaze.shows.mappers.ShowEntityMapper
import com.pablojmuratore.testtvmaze.shows.mappers.ShowInfoDtoMapper
import com.pablojmuratore.testtvmaze.shows.mappers.ShowScheduleDtoMapper
import com.pablojmuratore.testtvmaze.shows.repositories.IShowsRepository
import com.pablojmuratore.testtvmaze.shows.repositories.ShowsRepository
import com.pablojmuratore.testtvmaze.shows_database.repositories.IShowsDatabaseRepository
import com.pablojmuratore.testtvmaze.shows_network.repositories.IShowsNetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShowsModule {
    @Singleton
    @Provides
    fun provideShowEntityMapper(): ShowEntityMapper {
        return ShowEntityMapper()
    }

    @Singleton
    @Provides
    fun providePosterImageDtoMapper(): PosterImageDtoMapper {
        return PosterImageDtoMapper()
    }

    @Singleton
    @Provides
    fun provideShowScheduleDtoMapper(): ShowScheduleDtoMapper {
        return ShowScheduleDtoMapper()
    }

    @Singleton
    @Provides
    fun provideShowEmbeddedDataDtoMapper(episodeDtoMapper: EpisodeDtoMapper): ShowEmbeddedDataDtoMapper {
        return ShowEmbeddedDataDtoMapper(episodeDtoMapper)
    }

    @Singleton
    @Provides
    fun provideShowInfoDtoMapper(showDtoMapper: ShowDtoMapper): ShowInfoDtoMapper {
        return ShowInfoDtoMapper(showDtoMapper)
    }

    @Singleton
    @Provides
    fun provideShowsRepository(
        showsDatabaseRepository: IShowsDatabaseRepository,
        showsNetworkRepository: IShowsNetworkRepository,
        likedShowEntityMapper: LikedShowEntityMapper,
        showDtoMapper: ShowDtoMapper,
        showInfoDtoMapper: ShowInfoDtoMapper
    ): IShowsRepository {
        return ShowsRepository(
            showsDatabaseRepository,
            showsNetworkRepository,
            likedShowEntityMapper,
            showDtoMapper,
            showInfoDtoMapper
        )
    }

    @Singleton
    @Provides
    fun provideLikedShowsEntityMapper(): LikedShowEntityMapper {
        return LikedShowEntityMapper()
    }

    @Singleton
    @Provides
    fun provideShowDtoMapper(
        posterImageDtoMapper: PosterImageDtoMapper,
        showScheduleDtoMapper: ShowScheduleDtoMapper,
        showEmbeddedDataDtoMapper: ShowEmbeddedDataDtoMapper
    ): ShowDtoMapper {
        return ShowDtoMapper(posterImageDtoMapper, showScheduleDtoMapper, showEmbeddedDataDtoMapper)
    }

    @Singleton
    @Provides
    fun provideEpisodeDtoMapper(posterImageDtoMapper: PosterImageDtoMapper): EpisodeDtoMapper {
        return EpisodeDtoMapper(posterImageDtoMapper)
    }
}