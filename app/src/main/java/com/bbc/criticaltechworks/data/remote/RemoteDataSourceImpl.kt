package com.bbc.criticaltechworks.data.remote

import com.bbc.criticaltechworks.data.local.entity.NewsApiResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {

    override suspend fun getHeadLines(): NewsApiResponse {
        return apiService.getHeadlines()
    }
}