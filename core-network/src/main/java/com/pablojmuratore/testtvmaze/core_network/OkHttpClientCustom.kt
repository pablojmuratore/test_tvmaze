package com.pablojmuratore.testtvmaze.core_network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpClientCustom {
    companion object {
        private var okHttpClient: OkHttpClient? = null

        fun getLoggingInterceptor(): OkHttpClient {
            if (okHttpClient == null) {
                val logging = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

                okHttpClient = OkHttpClient().newBuilder()
                    .addInterceptor(logging)
                    .build()
            }

            return okHttpClient!!
        }
    }
}