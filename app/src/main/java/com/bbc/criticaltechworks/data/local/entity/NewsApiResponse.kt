package com.bbc.criticaltechworks.data.local.entity

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)