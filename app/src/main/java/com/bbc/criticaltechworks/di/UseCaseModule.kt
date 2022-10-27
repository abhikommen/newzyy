package com.bbc.criticaltechworks.di

import com.bbc.criticaltechworks.feature_news.domain.repository.NewsRepository
import com.bbc.criticaltechworks.feature_news.domain.use_case.GetArticles
import com.bbc.criticaltechworks.feature_news.domain.use_case.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideArticleUseCase(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getArticles = GetArticles(newsRepository)
        )
    }

}