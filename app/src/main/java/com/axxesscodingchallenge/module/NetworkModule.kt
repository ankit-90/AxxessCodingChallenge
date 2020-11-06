package com.axxesscodingchallenge.module

import com.axxesscodingchallenge.BuildConfig
import com.axxesscodingchallenge.api.AxxessCodingChallengeService
import com.axxesscodingchallenge.api.SecurityInterceptor
import com.axxesscodingchallenge.utils.CommonUtils
import com.axxesscodingchallenge.utils.Constants.CACHE_CONTROL
import com.axxesscodingchallenge.utils.Constants.PUBLIC_MAX_AGE
import com.axxesscodingchallenge.utils.Constants.PUBLIC_ONLY_IF_CACHED
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.imgur.com/"
val networkModule = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService(
            createHttpClient(),

            BASE_URL
        )
    }
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .addInterceptor(SecurityInterceptor())
    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(httpLoggingInterceptor)
    }
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        if (CommonUtils.isNetworkAvailable())
            requestBuilder.header(CACHE_CONTROL, PUBLIC_MAX_AGE + 5).build()
        else
            requestBuilder.header(
                CACHE_CONTROL,
                PUBLIC_ONLY_IF_CACHED + 60 * 60 * 24 * 7
            ).build()
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

/* function to build our Retrofit service */
fun createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): AxxessCodingChallengeService {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(AxxessCodingChallengeService::class.java)
}