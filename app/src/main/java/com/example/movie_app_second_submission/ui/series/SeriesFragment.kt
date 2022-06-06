package com.example.movie_app_second_submission.ui.series

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie_app_second_submission.core.ui.SeriesAdapter
import com.example.movie_app_second_submission.core.utils.AppConstants
import com.example.movie_app_second_submission.databinding.FragmentSeriesBinding
import com.example.movie_app_second_submission.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : Fragment() {

    private val viewModel: SeriesViewModel by viewModels()
    private lateinit var seriesAdapter: SeriesAdapter
    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            seriesAdapter = SeriesAdapter()
            seriesAdapter.onItemClick = { selectedSeries ->
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID, selectedSeries.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, AppConstants.SERIES)
                startActivity(intent)
            }

            showLoading()
            viewModel.series.observe(viewLifecycleOwner) { series ->
                seriesAdapter.submitData(lifecycle,series)
            }

            with(binding.rvSeries) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }
        }
    }

    private fun showLoading() {

        seriesAdapter.addLoadStateListener { loadState ->
            if(loadState.refresh is LoadState.Loading) {
                binding.pgLoading.visibility = View.VISIBLE
                binding.tvError.visibility = View.GONE
                binding.rvSeries.visibility = View.GONE
            } else if(loadState.refresh is LoadState.NotLoading) {
                binding.pgLoading.visibility = View.GONE
                binding.tvError.visibility = View.GONE
                binding.rvSeries.visibility = View.VISIBLE
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}