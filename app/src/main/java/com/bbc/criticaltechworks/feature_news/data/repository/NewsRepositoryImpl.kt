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

    override fun getArticle(): Flow<DataState<List<Article>>> = flow {
        try {
            emit(DataState.Loading)
            val remoteArticles = remoteDataSource.getHeadLines().articles
            localDataSource.insertArticles(remoteArticles)
            emit(DataState.Success(remoteArticles))
        } catch (e: Exception) {
            val localSavedArticles = localDataSource.getAllArticles()
            if (localSavedArticles.isNotEmpty()) {
                emit((DataState.Success(localSavedArticles)))
            } else {
                when(e){
                    is UnknownHostException -> emit(DataState.Error(Exception("Internet Not Found. ")))
                }
            }
        }
    }
}