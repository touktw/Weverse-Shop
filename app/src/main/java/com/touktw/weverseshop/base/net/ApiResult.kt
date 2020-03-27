package com.touktw.weverseshop.base.net

/**
 * Created by taekim on 2020-03-27
 */

data class ApiResult<T>(
        val code: Int,
        val data: T?,
        val message: String? = null
)