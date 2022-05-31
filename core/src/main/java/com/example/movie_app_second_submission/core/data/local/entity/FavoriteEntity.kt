package com.example.movie_app_second_submission.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,
    @ColumnInfo(name = "type")
    val type: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "date")
    val date: String? = null,
)