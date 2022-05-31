package com.example.movie_app_second_submission.ui.series

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.movie_app_second_submission.core.domain.usecase.series.ISeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(seriesUseCase: ISeriesUseCase) : ViewModel() {
    val series = LiveDataReactiveStreams.fromPublisher(seriesUseCase.getAllSeries())
}