package com.example.movie_app_second_submission.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.movie_app_second_submission.core.domain.model.Movie
import com.example.movie_app_second_submission.core.domain.usecase.movies.IMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: IMovieUseCase) : ViewModel() {

    fun getMoviePaging() : LiveData<PagingData<Movie>> {
        return LiveDataReactiveStreams.fromPublisher(movieUseCase.getAllMovies().cachedIn(viewModelScope))
    }

}