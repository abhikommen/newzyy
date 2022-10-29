package com.news.criticaltechworks.feature_news.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.news.criticaltechworks.feature_news.data.data_source.local.converters.DateTimeConverter
import com.news.criticaltechworks.feature_news.domain.model.Article


@Database(
    entities = [Article::class],
    version = 1,
)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}