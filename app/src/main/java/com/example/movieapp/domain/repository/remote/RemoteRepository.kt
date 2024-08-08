package com.example.movieapp.domain.repository.remote

import androidx.paging.PagingData
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow


interface RemoteRepository {
    fun getAllData() : Flowable<PagingData<PopularMovie>>
}