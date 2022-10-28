package com.bbc.criticaltechworks.feature_news.data.repository

import com.bbc.criticaltechworks.feature_news.domain.model.Article
import com.bbc.criticaltechworks.feature_news.domain.repository.NewsRepository
import java.net.UnknownHostException
import java.util.*

class FakeRepository : NewsRepository {

    private val remoteArticleList = buildList<Article> {
        repeat(10) {
            add(Article(Date(System.currentTimeMillis() - it * 60000)))
        }
    }

    var internetAvailable = true

    private var localList = mutableListOf<Article>()

    override suspend fun getRemoteArticle(): List<Article> {
        if (internetAvailable) {
            return remoteArticleList
        } else throw UnknownHostException("Mocking Internet Failure")
    }

    override suspend fun saveArticleInCache(articleList: List<Article>) {
        localList.clear()
        localList.addAll(buildList {
            repeat(5) {
                add(Article(Date(System.currentTimeMillis() - it * 60000)))
            }
        })
    }

    override suspend fun getCachedArticle(): List<Article> {
        return localList
    }

}