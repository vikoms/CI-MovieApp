package com.example.movie_app_second_submission.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie_app_second_submission.core.domain.usecase.favorite.IFavoriteUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val favoriteUseCase: IFavoriteUseCase
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(favoriteUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}