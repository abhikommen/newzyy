package com.bbc.criticaltechworks.feature_news.data.data_source.local.api

import com.bbc.criticaltechworks.feature_news.domain.model.Article

interface LocalDataSource {
    suspend fun insertArticles(list: List<Article>)
    suspend fun getAllArticles(): List<Article>
}