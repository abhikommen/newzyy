package com.bbc.criticaltechworks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbc.criticaltechworks.data.local.entity.Article
import com.bbc.criticaltechworks.data.repo.Repo
import com.bbc.criticaltechworks.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    private val _newArticlesState: MutableState<DataState<List<Article>>> =
        mutableStateOf(DataState.Loading)
    val newArticlesState: State<DataState<List<Article>>> = _newArticlesState

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            repo.getArticle().collect {
                _newArticlesState.value = it
            }
        }
    }


}