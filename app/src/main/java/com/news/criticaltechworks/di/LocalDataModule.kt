package com.news.criticaltechworks.di

import android.content.Context
import androidx.room.Room
import com.news.criticaltechworks.feature_news.data.data_source.local.api.LocalDataSource
import com.news.criticaltechworks.feature_news.data.data_source.local.api.LocalDataSourceImpl
import com.news.criticaltechworks.feature_news.data.data_source.local.AppDatabase
import com.news.criticaltechworks.feature_news.data.data_source.local.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context,
    ): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "mydb").build()
    }


    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): Dao {
        return appDatabase.dao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: Dao): LocalDataSource {
        return LocalDataSourceImpl(dao = dao)
    }

}