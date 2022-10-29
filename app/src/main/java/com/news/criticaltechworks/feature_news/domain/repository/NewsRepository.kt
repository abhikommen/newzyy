package com.news.criticaltechworks.feature_news.domain.repository

import com.news.criticaltechworks.feature_news.domain.model.Article

interface NewsRepository {
    suspend fun getRemoteArticle(): List<Article>
    suspend fun saveArticleInCache(articleList: List<Article>)
    suspend fun getCachedArticle(): List<Article>
}