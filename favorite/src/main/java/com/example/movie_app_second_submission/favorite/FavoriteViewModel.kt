package com.example.movie_app_second_submission.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.movie_app_second_submission.core.domain.model.Favorite
import com.example.movie_app_second_submission.core.domain.usecase.favorite.IFavoriteUseCase

class FavoriteViewModel(
    private val favoriteUseCase: IFavoriteUseCase
) : ViewModel() {
    fun getFavorites() : LiveData<List<Favorite>> {
        return LiveDataReactiveStreams.fromPublisher(favoriteUseCase.getFavorites())
    }
}