package com.example.movie_app_second_submission.core.domain.repository

import androidx.paging.PagingData
import com.example.movie_app_second_submission.core.data.Resource
import com.example.movie_app_second_submission.core.domain.model.Series
import io.reactivex.Flowable

interface ISeriesRepository {
    fun getAllSeries(): Flowable<PagingData<Series>>
    fun getDetailSeries(seriesId: Int): Flowable<Resource<Series>>

}