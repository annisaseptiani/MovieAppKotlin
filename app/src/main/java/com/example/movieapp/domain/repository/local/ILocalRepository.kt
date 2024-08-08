package com.example.movieapp.domain.repository.local

import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.domain.model.PopularMovie
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ILocalRepository @Inject constructor(private val
                                           dao: MovieDao) : LocalRepository {
    override fun addToFavorite(movie: PopularMovie): MovieEntity {
        TODO("Not yet implemented")
    }

    override fun getListFavorite() : Flowable<List<PopularMovie>> {
        return dao.allFavoriteMovie().map {
            data->
            data.map { dataMovie->
                dataMovie.toPopularMovie()
            }
        }
    }

    override fun deleteFavorite(id: Int) {
        TODO("Not yet implemented")
    }

    private fun MovieEntity.toPopularMovie() : PopularMovie {
        return PopularMovie(
            id = id,
            title = title,
            vote_average = vote_average,
            poster_path = poster_path,
            release_date = release_date,
            overview = overview
        )
    }

}