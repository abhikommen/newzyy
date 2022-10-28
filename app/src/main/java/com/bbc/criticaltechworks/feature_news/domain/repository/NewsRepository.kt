package com.bbc.criticaltechworks.feature_news.domain.repository

import com.bbc.criticaltechworks.feature_news.domain.model.Article
import com.bbc.criticaltechworks.common.utils.DataState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getRemoteArticle(): List<Article>
    suspend fun saveArticleInCache(articleList: List<Article>)
    suspend fun getCachedArticle(): List<Article>
}