package com.example.movie_app_second_submission.core.data.local.room

import androidx.room.*
import com.example.movie_app_second_submission.core.data.local.entity.FavoriteEntity

import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flowable<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFavoriteById(id: Int): Flowable<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteEntity): Completable

    @Delete
    fun deleteFavorite(favorite: FavoriteEntity): Completable

}