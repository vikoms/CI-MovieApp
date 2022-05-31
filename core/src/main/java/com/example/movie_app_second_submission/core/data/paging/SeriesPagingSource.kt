package com.example.movie_app_second_submission.core.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.movie_app_second_submission.core.data.remote.network.ApiService
import com.example.movie_app_second_submission.core.domain.model.Series
import com.example.movie_app_second_submission.core.utils.AppConstants
import com.example.movie_app_second_submission.core.utils.DataMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeriesPagingSource @Inject constructor(private val apiService: ApiService) : RxPagingSource<Int, Series>() {
    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Series>> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        val query = AppConstants.getBaseQuery().toMutableMap()
        query["page"] = position.toString()
        return apiService.getDiscoverTV(query)
            .subscribeOn(Schedulers.io())
            .map { DataMapper.mapSeriesResponseToDomain(it) }
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: List<Series>, position: Int): LoadResult<Int, Series> {
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