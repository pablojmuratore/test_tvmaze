package com.pablojmuratore.testtvmaze

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestTvMazeApp : Application() {
    companion object {
        private lateinit var testTvMazeApp: TestTvMazeApp

        fun getContext(): Context {
            return testTvMazeApp
        }
    }

    override fun onCreate() {
        super.onCreate()

        testTvMazeApp = this
    }
}