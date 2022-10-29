package com.news.criticaltechworks.common.utils

/**
 * Sealed class that has wraps data states, Loading, Success, Error
 */
sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}