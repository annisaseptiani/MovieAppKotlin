package com.example.movieapp.domain.model

import com.example.movieapp.data.model.Genre

data class DetailMovie(
    override val id :Int,
    override val title: String,
    override val vote_average : Double,
    override val poster_path : String,
    override val release_date: String,
    override val overview: String,
    val genres : List<Genre>
) : PopularMovie(id, title, vote_average, poster_path,
release_date, overview)