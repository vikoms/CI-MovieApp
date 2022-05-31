package com.example.movie_app_second_submission.core.data.local.datasource

import com.example.movie_app_second_submission.core.data.local.entity.FavoriteEntity
import com.example.movie_app_second_submission.core.data.local.room.FavoritesDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteLocalDataSource  @Inject constructor(private val favoritesDao: FavoritesDao) {

    fun insertFavorite(favorite: FavoriteEntity) =
        favoritesDao.insertFavorite(favorite)

    fun getAllFavorite() =
        favoritesDao.getAllFavorites()

    fun deleteFavorite(favorite: FavoriteEntity) =
        favoritesDao.deleteFavorite(favorite)

    fun getFavoriteById(id: Int) =
        favoritesDao.getFavoriteById(id)

}