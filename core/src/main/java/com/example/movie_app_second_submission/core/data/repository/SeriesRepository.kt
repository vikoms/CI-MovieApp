package com.example.movie_app_second_submission.core.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.movie_app_second_submission.core.data.Resource
import com.example.movie_app_second_submission.core.data.local.datasource.FavoriteLocalDataSource
import com.example.movie_app_second_submission.core.data.paging.SeriesPagingSource
import com.example.movie_app_second_submission.core.data.remote.network.ApiService
import com.example.movie_app_second_submission.core.domain.model.Series
import com.example.movie_app_second_submission.core.domain.repository.ISeriesRepository
import com.example.movie_app_second_submission.core.utils.DataMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeriesRepository @Inject constructor(
    private val apiService: ApiService,
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
    private val seriesPagingSource: SeriesPagingSource
) : ISeriesRepository {
    override fun getAllSeries(): Flowable<PagingData<Series>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                seriesPagingSource
            }
        ).flowable
    }


    @SuppressLint("CheckResult")
    override fun getDetailSeries(seriesId: Int): Flowable<Resource<Series>> {
        val resultData = PublishSubject.create<Resource<Series>>()

        val client = apiService.getTVDetail(seriesId.toString())

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                if (response != null) {
                    val data = DataMapper.mapSeriesResponseToDomain(response)
                    resultData.onNext(Resource.Success(data))
                } else {
                    resultData.onNext(Resource.Error("Data empty"))
                }
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Timber.e(error)
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}