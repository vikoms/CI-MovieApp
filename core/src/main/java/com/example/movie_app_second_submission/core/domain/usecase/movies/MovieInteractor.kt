package com.example.movie_app_second_submission.core.domain.usecase.movies

import androidx.paging.PagingData
import com.example.movie_app_second_submission.core.data.Resource
import com.example.movie_app_second_submission.core.domain.model.Movie
import com.example.movie_app_second_submission.core.domain.repository.IMovieRepository
import io.reactivex.Flowable
import javax.inject.Inject


class MovieInteractor @Inject constructor(private val movieRespository: IMovieRepository) :
    IMovieUseCase {
    override fun getDetailMovie(movieId: Int): Flowable<Resource<Movie>> {
        return movieRespository.getDetailMovie(movieId)
    }

    override fun getAllMovies(): Flowable<PagingData<Movie>> {
        return movieRespository.getAllMovies()
    }
}