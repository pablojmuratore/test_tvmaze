package com.pablojmuratore.testtvmaze.shows_network.models

data class EpisodeDTO(
    val id: Long,
    val name: String = "",
    val season: Int,
    val number: Int,
    val image: PosterImageDTO? = null,
    val summary: String? = null
)