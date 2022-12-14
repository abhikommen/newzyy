package com.news.criticaltechworks.feature_news.data.data_source.remote.api

import com.news.criticaltechworks.feature_news.data.data_source.remote.ApiService
import com.news.criticaltechworks.feature_news.domain.model.NewsApiResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {

    override suspend fun getHeadLines(): NewsApiResponse {
        return apiService.getHeadlines()
    }
}