package com.example.movieapp.data.mapper

import com.example.movieapp.data.model.DetailMovieResponse
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.Result
import com.example.movieapp.domain.model.DetailMovie
import com.example.movieapp.domain.model.PopularMovie
import javax.inject.Inject

class DataMapper @Inject constructor() {
    fun MapLocalData(entity: MovieEntity) : PopularMovie {
        return PopularMovie(
            id = entity.id,
            title = entity.title,
            vote_average = entity.vote_average,
            poster_path = entity.poster_path,
            release_date = entity.release_date,
            overview = entity.overview
        )
    }

    fun MapRemoteData(entity: Result) : PopularMovie {
        return PopularMovie(
            id = entity.id,
            title = entity.title,
            vote_average = entity.vote_average,
            poster_path = "https://image.tmdb.org/t/p/w500${entity.poster_path}",
            release_date = entity.release_date,
            overview = entity.overview
        )
    }

    fun MapDetailData(detailMovieResponse: DetailMovieResponse) : DetailMovie {
        return DetailMovie(
            id = detailMovieResponse.id,
            title = detailMovieResponse.title,
            vote_average = detailMovieResponse.vote_average,
            poster_path = "https://image.tmdb.org/t/p/w500${detailMovieResponse.poster_path}",
            release_date = detailMovieResponse.release_date,
            overview = detailMovieResponse.overview,
            genres = detailMovieResponse.genres
        )
    }
}