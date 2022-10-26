package com.bbc.criticaltechworks.di

import com.bbc.criticaltechworks.BuildConfig
import com.bbc.criticaltechworks.data.remote.ApiService
import com.bbc.criticaltechworks.data.remote.RemoteDataSource
import com.bbc.criticaltechworks.data.remote.RemoteDataSourceImpl
import com.zubi.muzyk.util.NetworkInterceptor
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