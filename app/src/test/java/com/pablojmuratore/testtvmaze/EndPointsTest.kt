package com.pablojmuratore.testtvmaze

import com.pablojmuratore.testtvmaze.network.TvMazeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class EndPointsTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()

        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Shows are returned`() = runBlocking {
        val shows = TvMazeApi.searchShows()

        assert(shows.isNotEmpty())
    }
}