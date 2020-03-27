package com.touktw.weverseshop.net

import com.touktw.weverseshop.base.net.ApiResponse
import com.touktw.weverseshop.base.net.BaseApi
import com.touktw.weverseshop.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by taekim on 2020-03-24
 */

interface WeverseShopApi : BaseApi {

    @GET("artists")
    fun getArtists(): Response<ApiResponse<List<ArtistDto>>>

    @GET("locales")
    fun getLocales(): Response<ApiResponse<List<LocaleDto>>>

    @GET("promotion")
    fun getAllPromotion(): Response<ApiResponse<List<PromotionDto>>>

    @GET("promotion/{id}")
    suspend fun getPromotion(@Path("id") artistId: String): Response<ApiResponse<List<PromotionDto>>>

    @GET("product")
    fun getAllProduct(): Deferred<Response<ApiResponse<List<ProductDto>>>>

    @GET("product/{id}")
    suspend fun getProduct(@Path("id") artistId: String): Response<ApiResponse<List<ProductDto>>>

    @GET("notice")
    suspend fun getNotice(): Response<ApiResponse<List<NoticeDto>>>
}