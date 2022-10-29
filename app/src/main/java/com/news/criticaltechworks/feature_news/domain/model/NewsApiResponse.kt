package com.news.criticaltechworks.feature_news.domain.model

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)