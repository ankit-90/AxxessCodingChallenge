package com.axxesscodingchallenge.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class SecurityInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.apply {
            addHeader("Authorization","Client-ID 137cda6b5008a7c")
        }
        return chain.proceed(request.build())
    }
}