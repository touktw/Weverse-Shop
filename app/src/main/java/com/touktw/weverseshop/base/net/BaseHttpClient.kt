package com.touktw.weverseshop.base.net

import okhttp3.Headers
import okhttp3.OkHttpClient

/**
 * Created by taekim on 2020-03-24
 */


abstract class BaseHttpClient {
    abstract val baseUrl: String
    abstract fun getHeaders(): Headers?

    val client: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder().let { builder ->
                    getHeaders()?.let {
                        builder.headers(it)
                    }
                    builder
                }.method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }
        builder.build()
    }
}