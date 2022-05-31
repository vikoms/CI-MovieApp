package com.example.movie_app_second_submission.core.data.remote.network

import com.example.movie_app_second_submission.core.data.remote.module.movie.ListMovieResponse
import com.example.movie_app_second_submission.core.data.remote.module.movie.MovieResponse
import com.example.movie_app_second_submission.core.data.remote.module.series.ListSeriesResponse
import com.example.movie_app_second_submission.core.data.remote.module.series.SeriesResponse
import com.example.movie_app_second_submission.core.utils.AppConstants
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @GET("discover/movie")
    fun getDiscoverMovie(@QueryMap queries: Map<String, String> = AppConstants.getBaseQuery()): Single<ListMovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: String,
        @QueryMap queries: Map<String, String> = AppConstants.getBaseQuery()
    ): Flowable<MovieResponse>

    @GET("discover/tv")
    fun getDiscoverTV(@QueryMap queries: Map<String, String> = AppConstants.getBaseQuery()): Single<ListSeriesResponse>

    @GET("tv/{id}")
    fun getTVDetail(
        @Path("id") id: String,
        @QueryMap queries: Map<String, String> = AppConstants.getBaseQuery()
    ): Flowable<SeriesResponse>
}