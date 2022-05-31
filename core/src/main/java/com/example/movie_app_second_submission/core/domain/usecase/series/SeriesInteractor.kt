package com.example.movie_app_second_submission.core.domain.usecase.series

import androidx.paging.PagingData
import com.example.movie_app_second_submission.core.domain.model.Series
import com.example.movie_app_second_submission.core.domain.repository.ISeriesRepository
import io.reactivex.Flowable
import javax.inject.Inject


class SeriesInteractor @Inject constructor(private val seriesRepository: ISeriesRepository) :
    ISeriesUseCase {
    override fun getAllSeries(): Flowable<PagingData<Series>> {
        return seriesRepository.getAllSeries()
    }

    override fun getDetailSeries(seriesId: Int): Flowable<com.example.movie_app_second_submission.core.data.Resource<Series>> {
        return seriesRepository.getDetailSeries(seriesId)
    }
}