package com.news.criticaltechworks.feature_news.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.criticaltechworks.R
import com.news.criticaltechworks.common.utils.DataState
import com.news.criticaltechworks.common.utils.appContext
import com.news.criticaltechworks.feature_news.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the HomeScreen that separates out view data ownership from UI controller logic.
 * @param useCases -> Use case that is responsible to get fetch Articles from repository.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: NewsUseCases
) : ViewModel() {

    private val _articleListState = mutableStateOf(NewsArticleState())
    val articleListState: State<NewsArticleState> = _articleListState

    private var getArticlesJob: Job? = null

    init {
        getArticle()
    }

    private fun getArticle() {
        getArticlesJob = viewModelScope.launch {
            useCases.getArticles().collect {
                when (it) {
                    DataState.Loading -> {
                        _articleListState.value = NewsArticleState(isLoading = true)
                    }

                    is DataState.Error -> {
                        _articleListState.value = NewsArticleState(
                            error = it.exception.message ?: appContext.getString(
                                R.string.something_went_wrong
                            )
                        )
                    }

                    is DataState.Success -> {
                        _articleListState.value = NewsArticleState(articleList = it.data)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getArticlesJob?.cancel()
    }

}

