package com.example.movieapp.domain.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.flowable
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.mapper.DataMapper
import com.example.movieapp.data.remote.MoviePagingSource
import com.example.movieapp.data.remote.RemoteApi
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class IListMovieRepository @Inject constructor(
    private val api: RemoteApi,
    private val localMapper: DataMapper,
    private val dao: MovieDao
) : ListMovieRepository {
    override fun getAllData(): Flowable<PagingData<PopularMovie>> {
        return Pager(
            config = PagingConfig(pageSize = 20), // Adjust pageSize as needed
            pagingSourceFactory = { MoviePagingSource(api) }
        ).flowable.map {
            pagingData->
            pagingData.map { result->
                localMapper.MapRemoteData(result)
            }
        }
    }

    override fun getListFavorite(): Flowable<List<PopularMovie>> {
        return dao.allFavoriteMovie().map {
                data->
            data.map { dataMovie->
                localMapper.MapLocalData(dataMovie)
            }
        }
    }

}