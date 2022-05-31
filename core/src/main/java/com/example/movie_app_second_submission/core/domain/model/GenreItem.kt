package com.example.movie_app_second_submission.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreItem(
    val name: String? = null,
    val id: Int? = null
) : Parcelable