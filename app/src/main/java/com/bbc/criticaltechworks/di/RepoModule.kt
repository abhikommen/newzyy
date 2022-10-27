package com.bbc.criticaltechworks.di

import com.bbc.criticaltechworks.feature_news.data.data_source.local.api.LocalDataSource
import com.bbc.criticaltechworks.feature_news.data.data_source.remote.api.RemoteDataSource
import com.bbc.criticaltechworks.feature_news.domain.repository.NewsRepository
import com.bbc.criticaltechworks.feature_news.data.repository.NewsRepositoryImpl
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
    fun providesRepo(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource = remoteDataSource, localDataSource = localDataSource)
    }

}