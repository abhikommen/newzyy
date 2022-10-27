package com.bbc.criticaltechworks.feature_news.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bbc.criticaltechworks.feature_news.domain.model.Article


@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}