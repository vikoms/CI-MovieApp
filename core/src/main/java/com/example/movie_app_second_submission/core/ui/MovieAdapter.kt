package com.example.movie_app_second_submission.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app_second_submission.core.databinding.ItemListBinding
import com.example.movie_app_second_submission.core.domain.model.Movie
import com.example.movie_app_second_submission.core.utils.AppConstants

class MovieAdapter : PagingDataAdapter<Movie,MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((Movie) -> Unit)? = null



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }


    inner class MovieViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        var movie: Movie? = null

        fun bind(item: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${AppConstants.BASE_URL_BACKDROP}${item.backdropPath}")
                    .into(imagePoster)
                tvTitle.text = item.title
                tvDate.text = item.releaseDate
            }
            movie = item
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(movie!!)
            }
        }

    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}