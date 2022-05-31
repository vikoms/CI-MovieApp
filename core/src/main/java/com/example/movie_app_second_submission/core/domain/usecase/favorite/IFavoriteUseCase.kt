package com.example.movie_app_second_submission.core.domain.usecase.favorite

import com.example.movie_app_second_submission.core.domain.model.Favorite
import io.reactivex.Flowable

interface IFavoriteUseCase {
    fun getFavorites(): Flowable<List<Favorite>>
    fun getFavoriteById(id: Int):Flowable<Favorite>
    fun insertFavorite(favorite: Favorite)
    fun deleteFavorite(favorite: Favorite)
}