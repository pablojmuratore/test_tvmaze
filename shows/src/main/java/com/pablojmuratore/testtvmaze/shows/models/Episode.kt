package com.pablojmuratore.testtvmaze.shows.models

data class Episode(
    val id: Long,
    val name: String = "",
    val season: Int,
    val number: Int,
    val image: PosterImage? = null,
    val summary: String? = null
)