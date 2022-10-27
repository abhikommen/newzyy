package com.bbc.criticaltechworks.feature_news.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bbc.criticaltechworks.feature_news.domain.model.Article

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

    @Query("SELECT * FROM article")
    suspend fun getAllArticles(): List<Article>

}