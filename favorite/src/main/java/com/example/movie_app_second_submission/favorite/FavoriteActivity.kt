package com.example.movie_app_second_submission.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie_app_first_submission.favorite.databinding.ActivityFavoriteBinding
import com.example.movie_app_second_submission.core.domain.model.Favorite
import com.example.movie_app_second_submission.core.ui.FavoriteAdapter
import com.example.movie_app_second_submission.di.FavoriteModuleDependencies
import com.example.movie_app_second_submission.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding

    private val favorites = ArrayList<Favorite>()
    private lateinit var favoriteAdapter: FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite Screen"
        favoriteAdapter = FavoriteAdapter()

        favoriteAdapter.onItemClick = { favorite ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, favorite.id)
            intent.putExtra(DetailActivity.EXTRA_TYPE, favorite.type)
            startActivity(intent)
        }


//        favoriteViewModel.movies.observe(this) { movies ->
//            movies.map {
//                favorites.add(
//                    Favorite(
//                        id = it.id,
//                        title = it.title,
//                        backdropPath = it.backdropPath,
//                        date = it.releaseDate,
//                        type = AppConstants.MOVIE
//                    )
//                )
//            }
//            favoriteAdapter.setData(favorites)
//        }
//
//        favoriteViewModel.series.observe(this) { series ->
//            series.map {
//                favorites.add(
//                    Favorite(
//                        id = it.id,
//                        title = it.name,
//                        backdropPath = it.backdropPath,
//                        date = it.firstAirDate,
//                        type = AppConstants.SERIES
//                    )
//                )
//            }
//            favoriteAdapter.setData(favorites)
//        }


        favoriteViewModel.getFavorites().observe(this) {
            favoriteAdapter.setData(it)
        }

        with(binding.rvFavorite) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        favorites.clear()
    }

}