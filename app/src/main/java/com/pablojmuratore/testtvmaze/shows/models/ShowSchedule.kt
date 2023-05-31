package com.pablojmuratore.testtvmaze.shows.models

data class ShowSchedule(
    val time: String = "",
    val days: List<String> = emptyList()
)