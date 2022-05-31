package com.example.movie_app_second_submission.core.di

import com.example.movie_app_second_submission.core.data.repository.FavoriteRepository
import com.example.movie_app_second_submission.core.data.repository.MovieRepository
import com.example.movie_app_second_submission.core.data.repository.SeriesRepository
import com.example.movie_app_second_submission.core.domain.repository.IFavoriteRepository
import com.example.movie_app_second_submission.core.domain.repository.IMovieRepository
import com.example.movie_app_second_submission.core.domain.repository.ISeriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providerMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    abstract fun provideSeriesRepository(seriesRepository: SeriesRepository): ISeriesRepository

    @Binds
    abstract fun provideFavoriteRepository(favoriteRepository: FavoriteRepository): IFavoriteRepository
}