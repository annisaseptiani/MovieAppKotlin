package com.example.movieapp.moviedetail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.model.DetailMovie
import com.example.movieapp.domain.repository.local.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {

    private val _saveState = MutableStateFlow<Boolean>(false)
    val saveState : StateFlow<Boolean> = _saveState

    private val compositeDisposable = CompositeDisposable()
    private val _detailMoview = MutableStateFlow<DetailMovie?>(null)
    val detailMovie : StateFlow<DetailMovie?> = _detailMoview

    init {
        resetState()
    }
    fun addToFavorite(detailMovie: DetailMovie) {
        detailRepository.addToFavorite(detailMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _saveState.value=true
            }
            .let { compositeDisposable.add(it) }
    }

    fun resetState() {
        _saveState.value = false
    }
    fun deleteFromFavorite(id: Int) {
        detailRepository.deleteFavorite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _saveState.value = false
            }
            .let { compositeDisposable.add(it) }
    }
    fun checkMovieSaved(id: Int) {
        detailRepository.checkMovieSaved(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                exist->
                _saveState.value = exist
            }, {
                error->
                Log.e("MovieDetailViewModel", "Error checking if movie saved")
            }).let { compositeDisposable.add(it) }
    }
    fun getDetailData(id:Int) {
        detailRepository.getDetailData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({_detail->
                _detailMoview.value = _detail
            }, {throwable->
                _detailMoview.value = null
                Log.d("DetailViewModel", throwable.message.toString())
            }).let {compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}