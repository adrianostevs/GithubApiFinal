package com.example.core.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Authorization", "token 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")
        request = builder.build()
        return chain.proceed(request)
    }
}