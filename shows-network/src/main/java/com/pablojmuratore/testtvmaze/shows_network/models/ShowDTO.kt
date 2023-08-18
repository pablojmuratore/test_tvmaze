package com.pablojmuratore.testtvmaze.shows_network.models

import com.squareup.moshi.Json

data class ShowDTO(
    val id: Long,
    val name: String,
    val image: PosterImageDTO? = null,
    val schedule: ShowScheduleDTO,
    val genres: List<String> = emptyList(),
    val summary: String? = null,
    @Json(name = "_embedded") val embeddedData: ShowEmbeddedDataDTO? = null
)