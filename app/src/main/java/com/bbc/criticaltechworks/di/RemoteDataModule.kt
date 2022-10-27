package com.bbc.criticaltechworks.di

import com.bbc.criticaltechworks.BuildConfig
import com.bbc.criticaltechworks.feature_news.data.data_source.remote.ApiService
import com.bbc.criticaltechworks.feature_news.data.data_source.remote.api.RemoteDataSource
import com.bbc.criticaltechworks.feature_news.data.data_source.remote.api.RemoteDataSourceImpl
import com.bbc.criticaltechworks.util.NetworkInterceptor
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
object RemoteDataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        clientBuilder.addInterceptor(NetworkInterceptor())
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
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