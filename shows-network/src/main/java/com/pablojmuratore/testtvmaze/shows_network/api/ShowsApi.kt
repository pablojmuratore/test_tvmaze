package com.pablojmuratore.testtvmaze.shows_network.api

import com.pablojmuratore.testtvmaze.core_network.BuildConfig
import com.pablojmuratore.testtvmaze.core_network.OkHttpClientCustom
import com.pablojmuratore.testtvmaze.shows_network.models.ShowDTO
import com.pablojmuratore.testtvmaze.shows_network.models.ShowInfoDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.TVMAZE_ENDPOINT)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(OkHttpClientCustom.getLoggingInterceptor())
    .build()

object ShowsApi : IShowsApi {
    private val retrofitService = retrofit.create(ITvMazeRetrofit::class.java)

    override suspend fun searchShows(query: String): List<ShowInfoDTO> {
        val completeQuery = ":$query"

        return retrofitService.searchShows(completeQuery)
    }

    override suspend fun loadShowDetail(showId: Long): ShowDTO {
        return retrofitService.loadShowDetail(showId)
    }
}

interface ITvMazeRetrofit {
    @GET("/search/shows")
    suspend fun searchShows(@Query("q") query: String): List<ShowInfoDTO>

    @GET("shows/{show_id}?embed=episodes")
    suspend fun loadShowDetail(@Path("show_id") showId: Long): ShowDTO
}