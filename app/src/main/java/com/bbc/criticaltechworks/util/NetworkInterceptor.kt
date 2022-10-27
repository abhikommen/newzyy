package com.bbc.criticaltechworks.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        Log.d("NetworkInterceptor", "Request : ${chain.request().url} ")
        return chain.proceed(request.build())
    }
}