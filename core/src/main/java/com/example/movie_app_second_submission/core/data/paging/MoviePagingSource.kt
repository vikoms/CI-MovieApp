package com.example.movie_app_second_submission.core.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.movie_app_second_submission.core.data.remote.network.ApiService
import com.example.movie_app_second_submission.core.domain.model.Movie
import com.example.movie_app_second_submission.core.utils.AppConstants
import com.example.movie_app_second_submission.core.utils.DataMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingSource @Inject constructor(private val apiService: ApiService) : RxPagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movie>> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        val query = AppConstants.getBaseQuery().toMutableMap()
        query["page"] = position.toString()
        return apiService.getDiscoverMovie(query)
            .subscribeOn(Schedulers.io())
            .map { DataMapper.mapMovieResponseToDomain(it) }
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: List<Movie>, position: Int): LoadResult<Int, Movie> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.size - 1) null else position + 1
        )
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}