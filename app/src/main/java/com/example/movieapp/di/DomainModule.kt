package com.example.movieapp.di

import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.mapper.DataMapper
import com.example.movieapp.data.remote.RemoteApi
import com.example.movieapp.domain.repository.local.IDetailRepository
import com.example.movieapp.domain.repository.local.DetailRepository
import com.example.movieapp.domain.repository.remote.IListMovieRepository
import com.example.movieapp.domain.repository.remote.ListMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideListRepository(dao: MovieDao,
                                dataMapper: DataMapper,
                                api: RemoteApi) : ListMovieRepository {
        return IListMovieRepository(dao = dao,
            localMapper = dataMapper, api = api)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(dao: MovieDao, dataMapper: DataMapper,
                                api: RemoteApi) : DetailRepository {
        return IDetailRepository(dao = dao,
            localMapper = dataMapper, api= api)
    }

    @Provides
    @Singleton
    fun provideDataMapper() : DataMapper {
        return DataMapper()
    }
}