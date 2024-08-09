package com.example.movieapp.data.model

data class DetailMovieResponse(
    val adult: Boolean,
    val id: Int,
    val genres: List<Genre>,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
