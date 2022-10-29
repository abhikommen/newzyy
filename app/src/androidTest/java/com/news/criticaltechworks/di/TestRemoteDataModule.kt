package com.news.criticaltechworks.di

import com.news.criticaltechworks.BuildConfig
import com.news.criticaltechworks.feature_news.data.data_source.remote.ApiService
import com.news.criticaltechworks.feature_news.data.data_source.remote.api.RemoteDataSource
import com.news.criticaltechworks.feature_news.data.data_source.remote.api.RemoteDataSourceImpl
import com.news.criticaltechworks.common.utils.NetworkInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestRemoteDataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        clientBuilder.addInterceptor(NetworkInterceptor())
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(clientBuilder.build())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
                )
            ).build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

}