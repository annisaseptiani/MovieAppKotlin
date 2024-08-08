package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.model.MovieEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie_table")
    fun allFavoriteMovie() : Flowable<List<MovieEntity>>

    @Query("DELETE FROM movie_table WHERE id = :id")
    fun deleteUser(id: Int)
}