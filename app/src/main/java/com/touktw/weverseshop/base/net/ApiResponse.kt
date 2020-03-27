package com.touktw.weverseshop.base.net

import retrofit2.Response

/**
 * Created by taekim on 2020-03-24
 */


data class ApiResponse<T>(
        val description: String? = null,
        val data: T? = null
)

fun <T> Response<ApiResponse<T>>.getResult(): ApiResult<T> {
    var t: T? = null
    var resultCode: Int
    when {
        isSuccessful -> {
            t = body()?.data
            resultCode = ApiResultCode.SUCCESS
        }
        else -> {
            resultCode = code()
        }
    }

    return ApiResult(resultCode, t, message())
}