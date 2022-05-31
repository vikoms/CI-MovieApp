package com.example.movie_app_second_submission.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie_app_second_submission.core.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract fun favoritesDao():FavoritesDao
}