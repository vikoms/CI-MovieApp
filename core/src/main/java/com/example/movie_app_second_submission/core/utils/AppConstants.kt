package com.example.movie_app_second_submission.core.utils

import com.example.movie_app_second_submission.core.BuildConfig


object AppConstants {
    val BASE_API = "https://api.themoviedb.org/3/"
    val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w154/"
    val BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/w500/"

    val MOVIE = "MOVIE"
    val SERIES = "SERIES"


    fun getBaseQuery(): Map<String, String> {
        val queryMap = HashMap<String, String>()
        queryMap["api_key"] = BuildConfig.TMDB_API_KEY
        queryMap["language"] = "en-US"

        return queryMap
    }
}