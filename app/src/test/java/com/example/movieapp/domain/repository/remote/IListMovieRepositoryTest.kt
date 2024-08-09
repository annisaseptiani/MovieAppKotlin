package com.example.movieapp.domain.repository.remote

import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.mapper.DataMapper
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.PopularMovieResponse
import com.example.movieapp.data.model.Result
import com.example.movieapp.data.remote.RemoteApi
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class IListMovieRepositoryTest {
    @Mock
    private lateinit var api: RemoteApi

    @Mock
    private lateinit var localMapper: DataMapper

    @Mock
    private lateinit var dao: MovieDao

    private lateinit var repository: IListMovieRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = IListMovieRepository(api, localMapper, dao)
    }

    @Test
    fun `getAllData should return mapped paging data`() = runTest {
        val mockResults = listOf(
            com.example.movieapp.data.model.Result(
                adult = false,
                backdrop_path = "aaaaaa.jpg",
                genre_ids = listOf(1,2,3),
                id =  1,
                original_language = "Eng",
                original_title = "Inception",
                overview =  "A mind-bending thriller.",
                popularity = 200.000,
                poster_path = "https://image.tmdb.org/t/p/w500/poster_path.jpg",
                release_date = "2010-07-16",
                title = "Inception",
                video = false,
                vote_average= 8.8,
                vote_count = 25780
            )
        )
        val mockResponse =PopularMovieResponse(results = mockResults,
            page = 1, total_pages = 300, total_results = 4000)
        val mockPagingData = PagingData.from(listOf(mockResponse))

        `when`(api.getListPopular(anyInt())).thenReturn(Single.just(mockResponse))

        val popularMovie = PopularMovie(id = 1, title = "Inception", vote_average = 8.8, poster_path = "/poster.jpg", release_date = "2010-07-16", overview = "A mind-bending thriller.")
        `when`(localMapper.MapRemoteData(mockResults.get(0))).thenReturn(popularMovie)

        val flowable = repository.getAllData()

        val pagingData = flowable.blockingFirst()

        val collectedItems = mutableListOf<PopularMovie>()
//        runTest {
//            pagingData.map { item ->
//                localMapper.MapRemoteData(item)
//            }.collectLatest {
//                collectedItems.add(it)
//            }
//        }

        assertEquals(1, collectedItems.size)
        assertEquals(popularMovie, collectedItems[0])

        verify(api).getListPopular(anyInt())
        verify(localMapper).MapRemoteData(mockResults.get(0))
    }

    @Test
    fun `getListFavorite should return mapped favorite movies`() {
        val movieEntity = MovieEntity(id = 1, title = "Inception", vote_average = 8.8, poster_path = "/poster.jpg", release_date = "2010-07-16", overview = "A mind-bending thriller.")
        val mockFavoriteMovies = listOf(movieEntity)

        `when`(dao.allFavoriteMovie()).thenReturn(Flowable.just(mockFavoriteMovies))

        val popularMovie = PopularMovie(id = 1, title = "Inception", vote_average = 8.8, poster_path = "/poster.jpg", release_date = "2010-07-16", overview = "A mind-bending thriller.")
        `when`(localMapper.MapLocalData(movieEntity)).thenReturn(popularMovie)

        val testSubscriber = repository.getListFavorite()
            .test()

        testSubscriber.assertValue { favoriteMovies ->
            favoriteMovies.size == 1 && favoriteMovies[0] == popularMovie
        }

        verify(dao).allFavoriteMovie()
        verify(localMapper).MapLocalData(movieEntity)
    }
}