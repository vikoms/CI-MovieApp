package com.example.movie_app_second_submission.core.di

import com.example.movie_app_second_submission.core.data.remote.network.ApiService
import com.example.movie_app_second_submission.core.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname,"sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
            .add(hostname,"sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname,"sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")

            .add(hostname,"sha256/Dm26HCFEayAfRvZrcnZPg4OwgaSqjbwzdH/mnDWN4io=")
            .add(hostname,"sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname,"sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

}