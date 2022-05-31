package com.example.movie_app_second_submission.core.di

import android.content.Context
import androidx.room.Room
import com.example.movie_app_second_submission.core.data.local.room.FavoritesDao
import com.example.movie_app_second_submission.core.data.local.room.MovieAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieAppDatabase  {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movie_app".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java, "movie_app.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }



    @Provides
    fun provideFavoritesDao(database: MovieAppDatabase): FavoritesDao = database.favoritesDao()
}