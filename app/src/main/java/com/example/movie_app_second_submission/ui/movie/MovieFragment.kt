package com.example.movie_app_second_submission.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie_app_second_submission.core.ui.LoadingStateAdapter
import com.example.movie_app_second_submission.core.ui.MovieAdapter
import com.example.movie_app_second_submission.core.utils.AppConstants
import com.example.movie_app_second_submission.databinding.FragmentMovieBinding
import com.example.movie_app_second_submission.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { movie ->
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID, movie.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, AppConstants.MOVIE)
                startActivity(intent)
            }

            showLoading()
            movieViewModel.getMoviePaging().observe(viewLifecycleOwner) { movies ->
                movieAdapter.submitData(lifecycle, movies)
            }

            with(binding.rvMovies) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading() {

        movieAdapter.addLoadStateListener { loadState ->
            if(loadState.refresh is LoadState.Loading)  {
                binding.pgLoading.visibility = View.VISIBLE
                binding.tvError.visibility = View.GONE
                binding.rvMovies.visibility = View.GONE
            } else if(loadState.refresh is LoadState.NotLoading) {
                binding.pgLoading.visibility = View.GONE
                binding.tvError.visibility = View.GONE
                binding.rvMovies.visibility = View.VISIBLE
            }
        }


        binding.rvMovies.adapter = movieAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                movieAdapter.retry()
            }
        )
    }

}