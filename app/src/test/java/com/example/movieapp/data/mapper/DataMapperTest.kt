package com.example.movieapp.data.mapper

import com.example.movieapp.data.model.DetailMovieResponse
import com.example.movieapp.data.model.Genre
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.Result
import com.example.movieapp.domain.model.DetailMovie
import com.example.movieapp.domain.model.PopularMovie
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class DataMapperTest {
    private val dataMapper = DataMapper()

//    @Test
//    fun `MapLocalData should correctly map MovieEntity to PopularMovie`() {
//        val movieEntity = MovieEntity(
//            id = 1,
//            title = "Inception",
//            vote_average = 8.8,
//            poster_path = "/poster_path.jpg",
//            release_date = "2010-07-16",
//            overview = "A mind-bending thriller."
//        )
//
//        val expected = PopularMovie(
//            id = 1,
//            title = "Inception",
//            vote_average = 8.8,
//            poster_path = "/poster_path.jpg",
//            release_date = "2010-07-16",
//            overview = "A mind-bending thriller."
//        )
//
//        val actual = dataMapper.MapLocalData(movieEntity)
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `MapRemoteData should correctly map Result to PopularMovie`() {
//        val result = Result(
//            adult = false,
//            backdrop_path = "aaaaaa.jpg",
//            genre_ids = listOf(1,2,3),
//            id =  1,
//            original_language = "Eng",
//            original_title = "Inception",
//            overview =  "A mind-bending thriller.",
//            popularity = 200.000,
//            poster_path = "https://image.tmdb.org/t/p/w500/poster_path.jpg",
//            release_date = "2010-07-16",
//            title = "Inception",
//            video = false,
//            vote_average= 8.8,
//            vote_count = 25780
//        )
//
//        val expected = PopularMovie(
//            id = 1,
//            title = "Inception",
//            vote_average = 8.8,
//            poster_path = "https://image.tmdb.org/t/p/w500/poster_path.jpg",
//            release_date = "2010-07-16",
//            overview = "A mind-bending thriller."
//        )
//
//        val actual = dataMapper.MapRemoteData(result)
//
//        assertEquals(expected, actual)
//    }

//    @Test
//    fun `MapDetailData should correctly map DetailMovieResponse to DetailMovie`() {
//        val detailMovieResponse = DetailMovieResponse(
//            adult = false,
//            id = 1,
//            title = "Inception",
//            vote_average = 8.8,
//            poster_path = "/poster_path.jpg",
//            release_date = "2010-07-16",
//            overview = "A mind-bending thriller.",
//            genres = listOf(Genre(1, "Horror"), Genre(2, "Mystery")),
//            popularity = 20000.000,
//            video = false,
//            vote_count = 300000
//        )
//
//        val expected = DetailMovie(
//            id = 1,
//            title = "Inception",
//            vote_average = 8.8,
//            poster_path = "https://image.tmdb.org/t/p/w500/poster_path.jpg",
//            release_date = "2010-07-16",
//            overview = "A mind-bending thriller.",
//            genres = listOf(Genre(1, "Horror"), Genre(2, "Mystery"))
//        )
//
//        val actual = dataMapper.MapDetailData(detailMovieResponse)
//
//        assertEquals(expected, actual)
//    }
}