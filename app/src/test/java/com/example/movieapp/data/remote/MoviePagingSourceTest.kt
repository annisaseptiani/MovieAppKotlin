package com.example.movieapp.data.remote

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.model.PopularMovieResponse
import com.example.movieapp.data.model.Result
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MoviePagingSourceTest {

    @Mock
    private lateinit var api: RemoteApi

    private lateinit var moviePagingSource: MoviePagingSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        moviePagingSource = MoviePagingSource(api)
    }

    @Test
    fun `load returns Page on successful response`() {
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

        `when`(api.getListPopular(1)).thenReturn(Single.just(mockResponse))

        val expectedResult = PagingSource.LoadResult.Page(
            data = mockResults,
            prevKey = null,
            nextKey = 2
        )

        val actualResult = moviePagingSource.loadSingle(PagingSource.LoadParams.Refresh(null, 1, false)).blockingGet()

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `load returns Error on failure response`() {
        val exception = Exception("Network error")

        `when`(api.getListPopular(1)).thenReturn(Single.error(exception))

        val actualResult = moviePagingSource.loadSingle(PagingSource.LoadParams.Refresh(null, 1, false)).blockingGet()

        assertTrue(actualResult is PagingSource.LoadResult.Error)
        assertEquals((actualResult as PagingSource.LoadResult.Error).throwable, exception)
    }

    @Test
    fun `getRefreshKey returns correct key`() {
        val mockResults = listOf(Result(
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
            vote_count = 25780))

        val pagingState = PagingState(
            pages = listOf(PagingSource.LoadResult.Page(data = mockResults, prevKey = 1, nextKey = 3)),
            anchorPosition = 0,
            config = PagingConfig(pageSize = 1),
            leadingPlaceholderCount = 0
        )

        val refreshKey = moviePagingSource.getRefreshKey(pagingState)

        assertEquals(2, refreshKey)
    }
}