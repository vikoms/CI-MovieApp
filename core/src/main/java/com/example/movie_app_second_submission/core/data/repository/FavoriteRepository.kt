package com.example.movie_app_second_submission.core.data.repository

import com.example.movie_app_second_submission.core.data.local.datasource.FavoriteLocalDataSource
import com.example.movie_app_second_submission.core.domain.model.Favorite
import com.example.movie_app_second_submission.core.domain.repository.IFavoriteRepository
import com.example.movie_app_second_submission.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
) : IFavoriteRepository {
    override fun getAllFavorites(): Flowable<List<Favorite>> {
        return favoriteLocalDataSource.getAllFavorite().map {
            DataMapper.mapFavoriteEntitiesToDomain(it)
        }
    }

    override fun getFavoriteById(id: Int): Flowable<Favorite> {
        return favoriteLocalDataSource.getFavoriteById(id).map {
            DataMapper.mapFavoriteEntityToDomain(it)
        }
    }

    override fun insertFavorite(favorite: Favorite) {
        val data = DataMapper.mapFavoriteDomainToEntity(favorite)
        favoriteLocalDataSource.insertFavorite(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun deleteFavorite(favorite: Favorite) {
        val data = DataMapper.mapFavoriteDomainToEntity(favorite)
        favoriteLocalDataSource.deleteFavorite(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}