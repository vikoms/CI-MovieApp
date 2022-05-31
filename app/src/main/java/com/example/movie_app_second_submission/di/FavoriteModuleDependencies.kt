package com.example.movie_app_second_submission.di

import com.example.movie_app_second_submission.core.domain.usecase.favorite.IFavoriteUseCase
import com.example.movie_app_second_submission.core.domain.usecase.movies.IMovieUseCase
import com.example.movie_app_second_submission.core.domain.usecase.series.ISeriesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun favoriteUseCase(): IFavoriteUseCase
}