package com.example.movie_app_second_submission.core.domain.repository

import androidx.paging.PagingData
import com.example.movie_app_second_submission.core.data.Resource
import com.example.movie_app_second_submission.core.domain.model.Movie
import io.reactivex.Flowable

interface IMovieRepository {
    fun getAllMovies(): Flowable<PagingData<Movie>>
    fun getDetailMovie(movieId: Int): Flowable<Resource<Movie>>
}