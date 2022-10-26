package com.bbc.criticaltechworks.data.repo

import com.bbc.criticaltechworks.data.local.LocalDataSource
import com.bbc.criticaltechworks.data.local.entity.Article
import com.bbc.criticaltechworks.data.remote.RemoteDataSource
import com.bbc.criticaltechworks.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class RepoImpl @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : Repo {

    override fun getArticle(): Flow<DataState<List<Article>>> = flow {
        try {
            emit(DataState.Loading)
            val remoteArticles = remoteDataSource.getHeadLines().articles
            localDataSource.insertArticles(remoteArticles)
            emit(DataState.Success(remoteArticles))
        } catch (e: java.lang.Exception) {
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