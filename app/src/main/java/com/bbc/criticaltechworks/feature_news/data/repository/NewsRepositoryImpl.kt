package com.bbc.criticaltechworks.feature_news.data.repository

import com.bbc.criticaltechworks.feature_news.data.data_source.local.api.LocalDataSource
import com.bbc.criticaltechworks.feature_news.domain.model.Article
import com.bbc.criticaltechworks.feature_news.data.data_source.remote.api.RemoteDataSource
import com.bbc.criticaltechworks.feature_news.domain.repository.NewsRepository
import com.bbc.criticaltechworks.common.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException
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