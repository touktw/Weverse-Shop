package com.touktw.weverseshop.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by taekim on 2020-03-24
 */

object WeverseShopService {
    private val client = WeverseShopHttpClient()
    fun get(): WeverseShopApi {
        return Retrofit.Builder()
            .baseUrl(client.baseUrl)
            .client(client.client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeverseShopApi::class.java)
    }
}