package com.news.criticaltechworks.feature_news.presentation.home

import com.news.criticaltechworks.feature_news.domain.model.Article

data class NewsArticleState(
    val isLoading: Boolean = false,
    val articleList: List<Article> = emptyList(),
    val error: String = ""
)