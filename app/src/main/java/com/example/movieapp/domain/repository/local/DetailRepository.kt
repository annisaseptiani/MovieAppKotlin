package com.example.movieapp.domain.repository.local

import com.example.movieapp.data.model.DetailMovieResponse
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.domain.model.DetailMovie
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface DetailRepository {
    fun addToFavorite(movie: DetailMovie) : Completable
    fun getDetailData(id: Int) : Single<DetailMovie>
    fun deleteFavorite(id : Int) : Completable

    fun checkMovieSaved(id: Int) : Single<Boolean>
}