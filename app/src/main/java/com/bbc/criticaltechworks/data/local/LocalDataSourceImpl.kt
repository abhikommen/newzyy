package com.bbc.criticaltechworks.data.local

import com.bbc.criticaltechworks.data.local.dao.Dao
import com.bbc.criticaltechworks.data.local.entity.Article
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(val dao: Dao) : LocalDataSource {

    override suspend fun insertArticles(list: List<Article>) {
        dao.insertArticles(articles = list)
    }

    override suspend fun getAllArticles(): List<Article> {
        return dao.getAllArticles()
    }


}