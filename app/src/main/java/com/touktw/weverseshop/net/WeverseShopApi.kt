package com.touktw.weverseshop.net

import com.touktw.weverseshop.base.net.BaseApi
import com.touktw.weverseshop.base.net.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by taekim on 2020-03-24
 */

interface WeverseShopApi : BaseApi {

    @GET("artists")
    fun getArtists(): Call<ApiResponse>

    @GET("locales")
    fun getLocales(): Call<ApiResponse>
}