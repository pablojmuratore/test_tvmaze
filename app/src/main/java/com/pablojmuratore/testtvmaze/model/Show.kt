package com.pablojmuratore.testtvmaze.model

import com.squareup.moshi.Json

data class Show(
    val id: Long,
    val name: String,
    val image: PosterImage? = null,
    val schedule: ShowSchedule,
    val genres: List<String> = emptyList(),
    val summary: String? = null,
    @Json(name = "_embedded") val embeddedData: ShowEmbeddedData? = null
)