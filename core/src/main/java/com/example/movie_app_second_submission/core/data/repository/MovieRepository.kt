package com.example.movie_app_second_submission.core.data.repository

import android.annotation.SuppressLint
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.movie_app_second_submission.core.data.Resource
import com.example.movie_app_second_submission.core.data.paging.MoviePagingSource
import com.example.movie_app_second_submission.core.data.remote.network.ApiService
import com.example.movie_app_second_submission.core.domain.model.Movie
import com.example.movie_app_second_submission.core.domain.repository.IMovieRepository
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
class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val moviePagingSource: MoviePagingSource
) : IMovieRepository {

    override fun getAllMovies(): Flowable<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                moviePagingSource
            }
        ).flowable
    }

    @SuppressLint("CheckResult")
    override fun getDetailMovie(movieId: Int): Flowable<Resource<Movie>> {
        val resultData = PublishSubject.create<Resource<Movie>>()

        val client = apiService.getMovieDetail(movieId.toString())

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                if (response != null) {
                    val data = DataMapper.mapMovieResponseToDomain(response)
                    resultData.onNext(Resource.Success(data))
                } else {
                    resultData.onNext(Resource.Error("Data Kosong"))
                }
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Timber.e(error)
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}