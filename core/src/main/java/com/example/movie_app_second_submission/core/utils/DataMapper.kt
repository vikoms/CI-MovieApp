package com.example.movie_app_second_submission.core.utils

import com.example.movie_app_second_submission.core.data.local.entity.FavoriteEntity
import com.example.movie_app_second_submission.core.data.remote.module.movie.ListMovieResponse
import com.example.movie_app_second_submission.core.data.remote.module.movie.MovieResponse
import com.example.movie_app_second_submission.core.data.remote.module.series.ListSeriesResponse
import com.example.movie_app_second_submission.core.data.remote.module.series.SeriesResponse
import com.example.movie_app_second_submission.core.domain.model.Favorite
import com.example.movie_app_second_submission.core.domain.model.Movie
import com.example.movie_app_second_submission.core.domain.model.Series

object DataMapper {


    fun mapMovieResponseToDomain(input: MovieResponse): Movie {

        val genre: String? = input.genres?.first()?.name

        return Movie(
            id = input.id,
            homepage = input.homepage,
            popularity = input.popularity,
            status = input.status,
            tagline = input.tagline,
            runtime = input.runtime,
            revenue = input.revenue,
            originalTitle = input.originalTitle,
            budget = input.budget,
            video = input.video,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            adult = input.adult,
            voteCount = input.voteCount,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            imdbId = input.imdbId,
            originalLanguage = input.originalLanguage,
            genre = genre
        )
    }

    fun mapSeriesResponseToDomain(input: ListSeriesResponse): List<Series> {

        var genre: String? = null
        input.results?.iterator()?.forEach {
            if (it.genres != null && it.genres.count() > 0) {
                genre = it.genres.first()?.name
            }
        }

        val seriesList = ArrayList<Series>()
        input.results?.map {
            seriesList.add(
                Series(
                    id = it.id,
                    genre = genre,
                    homepage = it.homepage,
                    popularity = it.popularity,
                    status = it.status,
                    tagline = it.tagline,
                    voteAverage = it.voteAverage,
                    adult = it.adult,
                    voteCount = it.voteCount,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    originalLanguage = it.originalLanguage,
                    type = it.type,
                    numberOfSeasons = it.numberOfSeasons,
                    numberOfEpisodes = it.numberOfEpisodes,
                    lastAirDate = it.lastAirDate,
                    inProduction = it.inProduction,
                    firstAirDate = it.firstAirDate,
                    originalName = it.originalName,
                    name = it.name,
                )
            )
        }
        return seriesList
    }

    fun mapSeriesResponseToDomain(input: SeriesResponse): Series {
        return Series(
            id = input.id,
            genre = input.genres?.first()?.name,
            homepage = input.homepage,
            popularity = input.popularity,
            status = input.status,
            tagline = input.tagline,
            voteAverage = input.voteAverage,
            adult = input.adult,
            voteCount = input.voteCount,
            overview = input.overview,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            originalLanguage = input.originalLanguage,
            type = input.type,
            numberOfSeasons = input.numberOfSeasons,
            numberOfEpisodes = input.numberOfEpisodes,
            lastAirDate = input.lastAirDate,
            inProduction = input.inProduction,
            firstAirDate = input.firstAirDate,
            originalName = input.originalName,
            name = input.name,
        )
    }

    fun mapMovieResponseToDomain(input: ListMovieResponse): List<Movie> {

        var genre: String? = null

        input.results?.iterator()?.forEach {
            if (it.genres != null && it.genres.count() > 0) {
                genre = it.genres.first()?.name
            }
        }

        val movieList = ArrayList<Movie>()
        input.results?.map {
            movieList.add(
                Movie(
                    id = it.id,
                    homepage = it.homepage,
                    popularity = it.popularity,
                    status = it.status,
                    tagline = it.tagline,
                    runtime = it.runtime,
                    revenue = it.revenue,
                    originalTitle = it.originalTitle,
                    budget = it.budget,
                    video = it.video,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    adult = it.adult,
                    voteCount = it.voteCount,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    imdbId = it.imdbId,
                    originalLanguage = it.originalLanguage,
                    genre = genre
                )
            )
        }
        return movieList
    }

    fun mapFavoriteEntitiesToDomain(input: List<FavoriteEntity>): List<Favorite> {
        return input.map {
            Favorite(
                it.id,
                it.backdropPath,
                it.type,
                it.title,
                it.date
            )
        }
    }

    fun mapFavoriteDomainToEntity(input: Favorite): FavoriteEntity {
        return FavoriteEntity(
            input.id,
            input.backdropPath,
            input.type,
            input.title,
            input.date
        )
    }

    fun mapFavoriteEntityToDomain(input: FavoriteEntity): Favorite {
        return Favorite(
            input.id,
            input.backdropPath,
            input.type,
            input.title,
            input.date
        )
    }

}