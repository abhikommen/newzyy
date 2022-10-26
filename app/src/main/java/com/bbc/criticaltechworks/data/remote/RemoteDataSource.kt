package com.bbc.criticaltechworks.data.remote

import com.bbc.criticaltechworks.data.local.entity.NewsApiResponse


interface RemoteDataSource {

    suspend fun getHeadLines() : NewsApiResponse

}