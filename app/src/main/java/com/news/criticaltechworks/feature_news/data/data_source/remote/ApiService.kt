package com.news.criticaltechworks.feature_news.data.data_source.remote

import com.news.criticaltechworks.BuildConfig
import com.news.criticaltechworks.feature_news.domain.model.NewsApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?sources=${BuildConfig.SOURCE}&apiKey=${BuildConfig.API_KEY}")
    suspend fun getHeadlines() : NewsApiResponse

}