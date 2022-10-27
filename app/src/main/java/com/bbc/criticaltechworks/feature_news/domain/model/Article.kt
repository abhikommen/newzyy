package com.bbc.criticaltechworks.feature_news.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbc.criticaltechworks.common.utils.JsonParcelable
import kotlinx.parcelize.Parcelize

@Entity(tableName = "article")
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = false)
    val publishedAt: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : JsonParcelable()