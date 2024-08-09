package com.example.movieapp.domain.repository.remote

import androidx.paging.PagingData
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Flowable


interface ListMovieRepository {
    fun getAllData() : Flowable<PagingData<PopularMovie>>

    fun getListFavorite() : Flowable<List<PopularMovie>>

}