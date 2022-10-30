package com.news.criticaltechworks.feature_news.domain.use_case

import com.news.criticaltechworks.common.utils.DataState
import com.news.criticaltechworks.feature_news.domain.model.Article
import com.news.criticaltechworks.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.UnknownHostException

/**
 * Use case which return contains the business logic to fetch ads from the remote api, save it
 * locally, and return it to the UI
 * @param newsRepository --> Repository class object whose purpose is to provide a clean API
 * for accessing data.
 */
class GetArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<DataState<List<Article>>> = flow {
        try {
            emit(DataState.Loading)
            val remoteArticles = newsRepository.getRemoteArticle().sortedByDescending {
                it.publishedAt.time
            }
            newsRepository.saveArticleInCache(remoteArticles)
            emit(DataState.Success(remoteArticles))
        } catch (e: Exception) {
            val localSavedArticles = newsRepository.getCachedArticle()
            if (localSavedArticles.isNotEmpty()) {
                emit((DataState.Success(localSavedArticles.sortedByDescending {
                    it.publishedAt.time
                })))
            } else {
                when (e) {
                    is UnknownHostException -> emit(DataState.Error(Exception("Internet Not Found. ")))
                }
            }
        }
    }
}