package com.bbc.criticaltechworks.feature_news.data.data_source.remote.api

import com.bbc.criticaltechworks.feature_news.domain.model.NewsApiResponse


interface RemoteDataSource {

    suspend fun getHeadLines() : NewsApiResponse

}