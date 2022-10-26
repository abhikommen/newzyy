package com.bbc.criticaltechworks.di

import com.bbc.criticaltechworks.data.local.LocalDataSource
import com.bbc.criticaltechworks.data.remote.RemoteDataSource
import com.bbc.criticaltechworks.data.repo.Repo
import com.bbc.criticaltechworks.data.repo.RepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun providesRepo(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): Repo {
        return RepoImpl(remoteDataSource = remoteDataSource, localDataSource = localDataSource)
    }

}