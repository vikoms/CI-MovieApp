package com.example.movie_app_second_submission.di

import com.example.movie_app_second_submission.core.domain.usecase.favorite.FavoriteInteractor
import com.example.movie_app_second_submission.core.domain.usecase.favorite.IFavoriteUseCase
import com.example.movie_app_second_submission.core.domain.usecase.movies.IMovieUseCase
import com.example.movie_app_second_submission.core.domain.usecase.movies.MovieInteractor
import com.example.movie_app_second_submission.core.domain.usecase.series.ISeriesUseCase
import com.example.movie_app_second_submission.core.domain.usecase.series.SeriesInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): IMovieUseCase

    @Binds
    @Singleton
    abstract fun provideSeriesUseCase(seriesInteractor: SeriesInteractor): ISeriesUseCase

    @Binds
    @Singleton
    abstract fun provideFavoriteUseCase(favoriteInteractor: FavoriteInteractor): IFavoriteUseCase

}