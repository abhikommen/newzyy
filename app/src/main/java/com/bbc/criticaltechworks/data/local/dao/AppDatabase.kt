package com.bbc.criticaltechworks.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bbc.criticaltechworks.data.local.entity.Article


@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}