package com.example.movie_app_second_submission.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val originalLanguage: String? = null,
    val imdbId: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val backdropPath: String? = null,
    val revenue: Int? = null,
    val genre: String? = null,
    val popularity: Double? = null,
    val id: Int? = null,
    val voteCount: Int? = null,
    val budget: Int? = null,
    val overview: String? = null,
    val originalTitle: String? = null,
    val runtime: Int? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val tagline: String? = null,
    val adult: Boolean? = null,
    val homepage: String? = null,
    val status: String? = null,
    val isFavorite: Boolean = false
) : Parcelable



