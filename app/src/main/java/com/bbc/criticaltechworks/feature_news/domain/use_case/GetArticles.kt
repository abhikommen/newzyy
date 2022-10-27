package com.bbc.criticaltechworks.feature_news.domain.use_case

import com.bbc.criticaltechworks.common.utils.DataState
import com.bbc.criticaltechworks.feature_news.domain.model.Article
import com.bbc.criticaltechworks.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<DataState<List<Article>>> {
        return newsRepository.getArticle()
    }

}