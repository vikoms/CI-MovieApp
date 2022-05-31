package com.example.Series_app_first_submission.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app_second_submission.core.databinding.ItemListBinding
import com.example.movie_app_second_submission.core.domain.model.Series
import com.example.movie_app_second_submission.core.utils.AppConstants

class SeriesAdapter : PagingDataAdapter<Series, SeriesAdapter.SeriesViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((Series) -> Unit)? = null



    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SeriesViewHolder(binding)
    }


    inner class SeriesViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        var Series: Series? = null

        fun bind(item: Series) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${AppConstants.BASE_URL_BACKDROP}${item.backdropPath}")
                    .into(imagePoster)
                tvTitle.text = item.name
                tvDate.text = item.firstAirDate
            }
            Series = item
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(Series!!)
            }
        }

    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Series>() {
            override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}