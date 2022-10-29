package com.news.criticaltechworks.di

import com.news.criticaltechworks.feature_news.data.data_source.local.api.LocalDataSource
import com.news.criticaltechworks.feature_news.data.data_source.remote.api.RemoteDataSource
import com.news.criticaltechworks.feature_news.domain.repository.NewsRepository
import com.news.criticaltechworks.feature_news.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestRepoModule {

    @Provides
    @Singleton
    fun providesRepo(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource = remoteDataSource, localDataSource = localDataSource)
    }

}