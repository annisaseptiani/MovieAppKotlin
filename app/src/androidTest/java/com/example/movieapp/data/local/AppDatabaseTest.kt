package com.example.movieapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieapp.data.model.MovieEntity
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        movieDao = db.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetMovie() {
        val movie = MovieEntity(
            id = 1,
            title = "Inception",
            vote_average = 8.8,
            poster_path = "/poster_path.jpg",
            release_date = "2010-07-16",
            overview = "A mind-bending thriller."
        )
        movieDao.addMovie(movie)
        val retrievedMovie = movieDao.checkMovie(id = movie.id)
        assertEquals(retrievedMovie.blockingGet()?.title, "Inception")
    }
}