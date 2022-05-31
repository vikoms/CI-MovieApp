package com.example.movie_app_second_submission.core.domain.repository

import com.example.movie_app_second_submission.core.domain.model.Favorite
import io.reactivex.Flowable

interface IFavoriteRepository {
    fun getAllFavorites(): Flowable<List<Favorite>>
    fun getFavoriteById(id: Int): Flowable<Favorite>
    fun insertFavorite(favorite: Favorite)
    fun deleteFavorite(favorite: Favorite)
}