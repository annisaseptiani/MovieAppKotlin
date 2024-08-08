package com.example.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title : String,
    val vote_average : Double,
    val poster_path : String,
    val release_date : String,
    val overview : String
)
