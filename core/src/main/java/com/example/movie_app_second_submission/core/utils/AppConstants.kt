package com.example.movie_app_second_submission.core.utils

import com.example.movie_app_second_submission.core.BuildConfig


object AppConstants {
    const val BASE_API = "https://api.themoviedb.org/3/"
    const val BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/w500/"

    const val MOVIE = "MOVIE"
    const val SERIES = "SERIES"


    fun getBaseQuery(): Map<String, String> {
        val queryMap = HashMap<String, String>()
        queryMap["api_key"] = BuildConfig.TMDB_API_KEY
        queryMap["language"] = "en-US"

        return queryMap
    }
}