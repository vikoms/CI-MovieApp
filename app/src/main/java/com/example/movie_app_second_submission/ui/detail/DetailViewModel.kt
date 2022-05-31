package com.example.movie_app_second_submission.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.movie_app_second_submission.core.data.Resource
import com.example.movie_app_second_submission.core.domain.model.Favorite
import com.example.movie_app_second_submission.core.domain.model.Movie
import com.example.movie_app_second_submission.core.domain.model.Series
import com.example.movie_app_second_submission.core.domain.usecase.favorite.IFavoriteUseCase
import com.example.movie_app_second_submission.core.domain.usecase.movies.IMovieUseCase
import com.example.movie_app_second_submission.core.domain.usecase.series.ISeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUseCase: IMovieUseCase,
    private val seriesUseCase: ISeriesUseCase,
    private val favoriteUseCase: IFavoriteUseCase
) : ViewModel() {

    fun getMovie(movieId: Int): LiveData<Resource<Movie>> {
        return LiveDataReactiveStreams.fromPublisher(movieUseCase.getDetailMovie(movieId))
    }

    fun getSeries(seriesId: Int): LiveData<Resource<Series>> {
        return LiveDataReactiveStreams.fromPublisher(seriesUseCase.getDetailSeries(seriesId))
    }

    fun setFavorite(favorite: Favorite,state: Boolean) {
        if(!state)
            favoriteUseCase.deleteFavorite(favorite)
        else
            favoriteUseCase.insertFavorite(favorite)
    }

    fun getFavorite(id: Int) : LiveData<Favorite> {
        return LiveDataReactiveStreams.fromPublisher(favoriteUseCase.getFavoriteById(id))
    }

}