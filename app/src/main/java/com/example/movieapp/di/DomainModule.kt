package com.example.movieapp.di

import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.remote.RemoteApi
import com.example.movieapp.domain.repository.local.ILocalRepository
import com.example.movieapp.domain.repository.local.LocalRepository
import com.example.movieapp.domain.repository.remote.IRemoteRepository
import com.example.movieapp.domain.repository.remote.RemoteRepository
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
    fun provideRemoteRepository(api: RemoteApi) : RemoteRepository {
        return IRemoteRepository(api)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: MovieDao) : LocalRepository {
        return ILocalRepository(dao)
    }
}