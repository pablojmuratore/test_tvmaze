package com.pablojmuratore.testtvmaze.shows.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_shows")
data class LikedShow(
    @PrimaryKey
    @ColumnInfo(name = "show_id")
    val showId: Long
)
