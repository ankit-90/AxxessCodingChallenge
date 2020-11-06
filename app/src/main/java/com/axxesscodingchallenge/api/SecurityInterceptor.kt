package com.axxesscodingchallenge.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
/**
 * @author Ankit Chandel
 * @since 04/11/20
 * <h1>SecurityInterceptor</h1>
 * <p>This class is mainly used to add custom headers to API calls</p>
 * */
class SecurityInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.apply {
            addHeader(ApiConstants.AUTH_KEY,ApiConstants.AUTH_VAL)
        }
        return chain.proceed(request.build())
    }
}