package com.example.movie_app_second_submission.core.data.remote.module.series

import com.google.gson.annotations.SerializedName

data class ListSeriesResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<SeriesResponse>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)

