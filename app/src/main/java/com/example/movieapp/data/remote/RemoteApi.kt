package com.example.movieapp.data.remote

import com.example.movieapp.data.model.DetailMovieResponse
import com.example.movieapp.data.model.PopularMovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApi {

    @GET("movie/popular")
    fun getListPopular(@Query("page") page: Int) : Single<PopularMovieResponse>

    @GET("movie/{movieId}")
    fun getDetailMovie(@Path("movieId") movieId : Long) : Single<DetailMovieResponse>
}