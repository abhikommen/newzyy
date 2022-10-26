package com.bbc.criticaltechworks.data.local

import com.bbc.criticaltechworks.data.local.entity.Article

interface LocalDataSource {
    suspend fun insertArticles(list: List<Article>)
    suspend fun getAllArticles(): List<Article>
}