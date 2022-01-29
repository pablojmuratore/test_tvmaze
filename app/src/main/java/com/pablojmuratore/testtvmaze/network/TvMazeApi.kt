package com.pablojmuratore.testtvmaze.network

import com.pablojmuratore.testtvmaze.BuildConfig
import com.pablojmuratore.testtvmaze.model.ShowInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.TVMAZE_ENDPOINT)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(OkHttpClientCustom.getLoggingInterceptor())
    .build()

object TvMazeApi : ITvMazeApi {
    private val retrofitService = retrofit.create(ITvMazeRetrofit::class.java)

    override suspend fun searchShows(query: String): List<ShowInfo> {
        val completeQuery = ":$query"

        return retrofitService.searchShows(completeQuery)
    }
}

interface ITvMazeRetrofit {
    @GET("/search/shows")
    suspend fun searchShows(@Query("q") query: String): List<ShowInfo>
}