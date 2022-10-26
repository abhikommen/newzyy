package com.bbc.criticaltechworks.data.repo

import com.bbc.criticaltechworks.data.local.entity.Article
import com.bbc.criticaltechworks.util.DataState
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun getArticle(): Flow<DataState<List<Article>>>
}