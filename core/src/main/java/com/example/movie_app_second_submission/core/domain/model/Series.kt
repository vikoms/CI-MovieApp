package com.example.movie_app_second_submission.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    val originalLanguage: String? = null,
    val numberOfEpisodes: Int? = null,
    val type: String? = null,
    val backdropPath: String? = null,
    val genre: String? = null,
    val popularity: Double? = null,
    val id: Int? = null,
    val numberOfSeasons: Int? = null,
    val voteCount: Int? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val originalName: String? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val tagline: String? = null,
    val adult: Boolean? = null,
    val inProduction: Boolean? = null,
    val lastAirDate: String? = null,
    val homepage: String? = null,
    val status: String? = null,
    val isFavorite: Boolean = false
) : Parcelable

