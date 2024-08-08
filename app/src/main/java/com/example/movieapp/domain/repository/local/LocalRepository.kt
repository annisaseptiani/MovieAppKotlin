package com.example.movieapp.domain.repository.local

import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Flowable

interface LocalRepository {
    fun addToFavorite(movie: PopularMovie) : MovieEntity
    fun getListFavorite() : Flowable<List<PopularMovie>>
    fun deleteFavorite(id : Int)
}