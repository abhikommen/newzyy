package com.news.criticaltechworks.feature_news.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.news.criticaltechworks.common.utils.JsonParcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "article")
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = false)
    val publishedAt: Date,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
) : JsonParcelable()