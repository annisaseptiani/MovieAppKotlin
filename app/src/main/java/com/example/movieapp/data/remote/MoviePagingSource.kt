package com.example.movieapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.movieapp.data.model.Result
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException

class MoviePagingSource(private val api: RemoteApi) : RxPagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
                anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Result>> {
        val page = params.key ?: 1
        return api.getListPopular(page)
            .subscribeOn(Schedulers.io())
            .map { response ->
                if (response.results.isNotEmpty()) {
                    LoadResult.Page(
                        data = response.results,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (response.results.isEmpty()) null else page + 1
                    ) as LoadResult<Int, Result>
                } else {
                    LoadResult.Error(Exception("No data"))
                }

            }
            .onErrorReturn { e ->
                Error(e)
            }
    }
}