package com.example.movieapp.domain.model

public open class PopularMovie(
    open val id :Int,
    open val title: String,
    open val vote_average : Double,
    open val poster_path : String,
    open val release_date: String,
    open val overview: String)