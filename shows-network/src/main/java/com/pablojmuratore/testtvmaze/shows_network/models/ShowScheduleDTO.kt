package com.pablojmuratore.testtvmaze.shows_network.models

data class ShowScheduleDTO(
    val time: String = "",
    val days: List<String> = emptyList()
)