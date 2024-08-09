package com.example.movieapp.domain.repository.local

import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.mapper.DataMapper
import com.example.movieapp.data.model.DetailMovieResponse
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.remote.RemoteApi
import com.example.movieapp.domain.model.DetailMovie
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class IDetailRepository @Inject
constructor(private val dao: MovieDao,
            private val localMapper : DataMapper,
    private val api: RemoteApi) : DetailRepository {
    override fun addToFavorite(movie: DetailMovie) : Completable {
        return Completable.fromAction {
            dao.addMovie(
                MovieEntity(
                    id = movie.id, title = movie.title,
                    vote_average = movie.vote_average, release_date = movie.release_date,
                    overview = movie.overview, poster_path = movie.poster_path
                )
            )
        }
    }

    override fun getDetailData(id:Int): Single<DetailMovie> {
        return api.getDetailMovie(id).map {
            localMapper.MapDetailData(it)
        }
    }

    override fun deleteFavorite(id: Int) : Completable {
        return Completable.fromAction {
            dao.deleteUser(id)
        }
    }

    override fun checkMovieSaved(id: Int) : Single<Boolean> {
       return dao.checkMovie(id)
           .isEmpty
           .map { isEmpty -> !isEmpty }
    }


}