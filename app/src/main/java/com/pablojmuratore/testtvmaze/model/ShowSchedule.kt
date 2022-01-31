package com.pablojmuratore.testtvmaze.model

data class ShowSchedule(
    val time: String = "",
    val days: List<String> = emptyList()
)