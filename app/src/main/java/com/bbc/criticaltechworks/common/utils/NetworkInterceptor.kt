package com.bbc.criticaltechworks.common.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptors are a powerful mechanism that can monitor, rewrite, and retry calls.
 * Here's a simple interceptor that logs the outgoing request and the incoming ..
 */
class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        Log.d("NetworkInterceptor", "Request : ${chain.request().url} ")
        return chain.proceed(request.build())
    }
}