package com.zeeb.newsindo.data.services

import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var requestBody = chain.request()
        requestBody = requestBody.newBuilder()
            .build()
        return chain.proceed(requestBody)
    }

}