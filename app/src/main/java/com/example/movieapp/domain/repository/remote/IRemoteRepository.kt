package com.example.movieapp.domain.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.flowable
import com.example.movieapp.data.model.Result
import com.example.movieapp.data.remote.MoviePagingSource
import com.example.movieapp.data.remote.RemoteApi
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IRemoteRepository @Inject constructor(
    private val api: RemoteApi
) : RemoteRepository {
    override fun getAllData(): Flowable<PagingData<PopularMovie>> {
        return Pager(
            config = PagingConfig(pageSize = 20), // Adjust pageSize as needed
            pagingSourceFactory = { MoviePagingSource(api) }
        ).flowable.map {
            pagingData->
            pagingData.map { result->
                result.toPopularMovie()
            }
        }
    }

    fun Result.toPopularMovie(): PopularMovie {
        return PopularMovie(
            id = id,
            title = title,
            overview = overview,
            poster_path = "https://image.tmdb.org/t/p/w300${poster_path}",
            release_date = release_date,
            vote_average = vote_average
        )
    }
}