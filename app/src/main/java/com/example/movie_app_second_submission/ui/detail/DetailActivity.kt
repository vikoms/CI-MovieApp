package com.example.movie_app_second_submission.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.movie_app_second_submission.R
import com.example.movie_app_second_submission.core.data.Resource
import com.example.movie_app_second_submission.core.domain.model.Favorite
import com.example.movie_app_second_submission.core.domain.model.Movie
import com.example.movie_app_second_submission.core.domain.model.Series
import com.example.movie_app_second_submission.core.utils.AppConstants
import com.example.movie_app_second_submission.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding
    private var id: Int? = null
    private lateinit var type: String
    private var statusFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            id = intent.getIntExtra(EXTRA_ID, 0)
            type = intent.getStringExtra(EXTRA_TYPE).toString()
        }

        if (type == AppConstants.MOVIE)
            supportActionBar?.title = "Detail Movie"
        else
            supportActionBar?.title = "Detail Series"


        if (id != null || id != 0) {
            getFavoriteById(id!!)
            if (type == AppConstants.MOVIE) {
                viewModel.getMovie(id!!).observe(this) { movie ->
                    when (movie) {
                        is Resource.Loading -> {
                            binding.pgLoading.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.pgLoading.visibility = View.GONE
                            initViewMovie(movie.data)
                        }
                        is Resource.Error -> {
                            binding.pgLoading.visibility = View.GONE
                            Toast.makeText(this, movie.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                viewModel.getSeries(id!!).observe(this) { series ->
                    when (series) {
                        is Resource.Loading -> {
                            binding.pgLoading.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.pgLoading.visibility = View.GONE
                            initViewSeries(series.data)
                        }
                        is Resource.Error -> {
                            binding.pgLoading.visibility = View.GONE
                            Toast.makeText(this, series.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }


        }



    }

    private fun initViewMovie(movie: Movie?) {

        binding.tvTitle.text = movie?.title
        binding.tvDesc.text = movie?.overview
        binding.tvRating.text = movie?.voteAverage.toString()
        binding.tvGenre.text = movie?.genre
        Glide.with(this)
            .load("${AppConstants.BASE_URL_BACKDROP}${movie?.backdropPath}")
            .into(binding.imgPoster)

        binding.btnFavorite.setOnClickListener {
            statusFavorite = !statusFavorite
            val favorite = Favorite(
                id = movie?.id,
                title = movie?.title,
                backdropPath = movie?.backdropPath,
                date = movie?.releaseDate,
                type = AppConstants.MOVIE
            )
            viewModel.setFavorite(favorite, statusFavorite)
            setStatusFavorite()
        }
    }

    private fun initViewSeries(data: Series?) {

        binding.tvTitle.text = data?.name
        binding.tvDesc.text = data?.overview
        binding.tvRating.text = data?.voteAverage.toString()
        binding.tvGenre.text = data?.genre
        Glide.with(this)
            .load("${AppConstants.BASE_URL_BACKDROP}${data?.backdropPath}")
            .into(binding.imgPoster)

        binding.btnFavorite.setOnClickListener {
            statusFavorite = !statusFavorite
            val favorite = Favorite(
                id = data?.id,
                title = data?.name,
                backdropPath = data?.backdropPath,
                date = data?.firstAirDate,
                type = AppConstants.SERIES
            )
            viewModel.setFavorite(favorite, statusFavorite)
            setStatusFavorite()
        }
    }

    private fun getFavoriteById(id: Int) {
        viewModel.getFavorite(id).observe(this) {
            statusFavorite = it != null
            setStatusFavorite()
        }
    }

    private fun setStatusFavorite() {
        if (statusFavorite) {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_fill
                )
            )
        } else {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_TYPE = "EXTRA_TYPE"
    }
}