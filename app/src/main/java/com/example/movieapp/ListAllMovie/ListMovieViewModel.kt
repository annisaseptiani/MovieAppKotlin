package com.example.movieapp.ListAllMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.domain.model.PopularMovie
import com.example.movieapp.domain.repository.remote.ListMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.reactive.asFlow
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val repository: ListMovieRepository
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val pagingFlow : Flowable<PagingData<PopularMovie>> = repository.getAllData()
    val paging : Flow<PagingData<PopularMovie>> = pagingFlow.asFlow().cachedIn(viewModelScope)

    private val _favoriteMovies = MutableStateFlow<List<PopularMovie>>(emptyList())
    val favoriteMovies : StateFlow<List<PopularMovie>> = _favoriteMovies

    init {
        showLocalData()
    }

    private fun showLocalData() {
        repository.getListFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({movies ->
                if (movies.isNullOrEmpty()) {
                    _favoriteMovies.value = emptyList()
                } else {
                    _favoriteMovies.value = if (movies.size < 10) movies else movies.take(10)
                }
            }, { throwable->

            }).let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}