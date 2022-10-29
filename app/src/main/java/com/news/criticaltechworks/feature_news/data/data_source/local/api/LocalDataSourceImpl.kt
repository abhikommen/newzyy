package com.news.criticaltechworks.feature_news.data.data_source.local.api

import com.news.criticaltechworks.feature_news.data.data_source.local.Dao
import com.news.criticaltechworks.feature_news.domain.model.Article
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(val dao: Dao) : LocalDataSource {

    override suspend fun insertArticles(list: List<Article>) {
        dao.insertArticles(articles = list)
    }

    override suspend fun getAllArticles(): List<Article> {
        return dao.getAllArticles()
    }


}