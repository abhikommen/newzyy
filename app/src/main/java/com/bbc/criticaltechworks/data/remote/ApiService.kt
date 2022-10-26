package com.bbc.criticaltechworks.data.remote

import com.bbc.criticaltechworks.BuildConfig
import com.bbc.criticaltechworks.data.local.entity.NewsApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?sources=${BuildConfig.SOURCE}&apiKey=${BuildConfig.API_KEY}")
    suspend fun getHeadlines() : NewsApiResponse

}