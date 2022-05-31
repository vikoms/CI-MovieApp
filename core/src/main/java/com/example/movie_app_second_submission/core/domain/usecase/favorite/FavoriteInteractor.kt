package com.example.movie_app_second_submission.core.domain.usecase.favorite

import com.example.movie_app_second_submission.core.domain.model.Favorite
import com.example.movie_app_second_submission.core.domain.repository.IFavoriteRepository
import io.reactivex.Flowable
import javax.inject.Inject

class FavoriteInteractor  @Inject constructor(private val favoriteRepository: IFavoriteRepository) : IFavoriteUseCase {
    override fun getFavorites(): Flowable<List<Favorite>> {
        return favoriteRepository.getAllFavorites()
    }

    override fun getFavoriteById(id: Int): Flowable<Favorite> {
        return favoriteRepository.getFavoriteById(id)
    }

    override fun insertFavorite(favorite: Favorite) {
        favoriteRepository.insertFavorite(favorite)
    }

    override fun deleteFavorite(favorite: Favorite) {
        favoriteRepository.deleteFavorite(favorite)
    }
}