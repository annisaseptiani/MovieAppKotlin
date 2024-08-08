package com.example.movieapp.data.remote

import com.example.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestWithApiKey: Request = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(requestWithApiKey)
    }

}