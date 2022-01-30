package com.pablojmuratore.testtvmaze.model

data class Episode(
    val id: Long,
    val name: String = "",
    val season: Int,
    val number: Int
)