package com.news.criticaltechworks.feature_news.data.repository

import com.news.criticaltechworks.feature_news.data.data_source.local.api.LocalDataSource
import com.news.criticaltechworks.feature_news.domain.model.Article
import com.news.criticaltechworks.feature_news.data.data_source.remote.api.RemoteDataSource
import com.news.criticaltechworks.feature_news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : NewsRepository {


    override suspend fun getRemoteArticle(): List<Article> {
        return remoteDataSource.getHeadLines().articles
    }

    override suspend fun saveArticleInCache(articleList: List<Article>) {
        return localDataSource.insertArticles(articleList)
    }

    override suspend fun getCachedArticle(): List<Article> {
        return localDataSource.getAllArticles()
    }
}