package com.example.movie_app_second_submission.core.domain.usecase.movies

import androidx.paging.PagingData
import com.example.movie_app_second_submission.core.data.Resource
import com.example.movie_app_second_submission.core.domain.model.Movie
import io.reactivex.Flowable

interface IMovieUseCase {
    fun getDetailMovie(movieId: Int): Flowable<Resource<Movie>>
    fun getAllMovies() : Flowable<PagingData<Movie>>
}