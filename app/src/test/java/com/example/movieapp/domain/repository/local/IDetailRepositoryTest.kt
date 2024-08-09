package com.example.movieapp.domain.repository.local

import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.mapper.DataMapper
import com.example.movieapp.data.model.DetailMovieResponse
import com.example.movieapp.data.model.Genre
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.remote.RemoteApi
import com.example.movieapp.domain.model.DetailMovie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class IDetailRepositoryTest {
    @Mock
    private lateinit var dao: MovieDao

    @Mock
    private lateinit var localMapper: DataMapper

    @Mock
    private lateinit var api: RemoteApi

    private lateinit var repository: IDetailRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = IDetailRepository(dao, localMapper, api)
    }

    @Test
    fun `addToFavorite should add movie to dao`() {
        val detailMovie = DetailMovie(
            id = 1,
            title = "Inception",
            vote_average = 8.8,
            release_date = "2010-07-16",
            overview = "A mind-bending thriller.",
            poster_path = "/poster_path.jpg",
            genres = listOf(Genre(1, "horor"))
        )

        `when`(dao.addMovie(any(MovieEntity::class.java)))

        repository.addToFavorite(detailMovie)
            .test()
            .assertComplete()

        verify(dao).addMovie(any(MovieEntity::class.java))
    }

    @Test
    fun `getDetailData should return mapped detail movie`() {
        val id = 1
        val detailMovieResponse = mock(DetailMovieResponse::class.java)
        val detailMovie = DetailMovie(
            id = 1,
            title = "Inception",
            vote_average = 8.8,
            release_date = "2010-07-16",
            overview = "A mind-bending thriller.",
            poster_path = "/poster_path.jpg",
            genres = listOf(Genre(1,"horor"))
        )

        `when`(api.getDetailMovie(id)).thenReturn(Single.just(detailMovieResponse))
        `when`(localMapper.MapDetailData(detailMovieResponse)).thenReturn(detailMovie)

        repository.getDetailData(id)
            .test()
            .assertValue(detailMovie)

        verify(api).getDetailMovie(id)
        verify(localMapper).MapDetailData(detailMovieResponse)
    }

    @Test
    fun `deleteFavorite should delete movie from dao`() {
        val id = 1

        `when`(dao.deleteUser(id))

        repository.deleteFavorite(id)
            .test()
            .assertComplete()

        verify(dao).deleteUser(id)
    }

    @Test
    fun `checkMovieSaved should return true if movie exists`() {
        val id = 1
        val movieEntity = MovieEntity(
            id = id,
            title = "Inception",
            vote_average = 8.8,
            release_date = "2010-07-16",
            overview = "A mind-bending thriller.",
            poster_path = "/poster_path.jpg"
        )

        `when`(dao.checkMovie(id)).thenReturn(Maybe.just(movieEntity).cache())

        repository.checkMovieSaved(id)
            .test()
            .assertValue(true)

        verify(dao).checkMovie(id)
    }

    @Test
    fun `checkMovieSaved should return false if movie does not exist`() {
        val id = 1

        // Mocking Maybe.empty to simulate that the movie does not exist
        `when`(dao.checkMovie(id)).thenReturn(null)

        repository.checkMovieSaved(id)
            .test()
            .assertValue(false)

        verify(dao).checkMovie(id)
    }
}