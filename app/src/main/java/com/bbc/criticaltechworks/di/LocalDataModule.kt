package com.bbc.criticaltechworks.di

import android.content.Context
import androidx.room.Room
import com.bbc.criticaltechworks.data.local.LocalDataSource
import com.bbc.criticaltechworks.data.local.LocalDataSourceImpl
import com.bbc.criticaltechworks.data.local.dao.AppDatabase
import com.bbc.criticaltechworks.data.local.dao.Dao
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