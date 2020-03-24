package com.touktw.weverseshop.net.response

import com.touktw.weverseshop.base.net.BaseResponse
import com.touktw.weverseshop.model.LocaleCurrencyDto

/**
 * Created by taekim on 2020-03-24
 */

data class LocaleResponse(
    val data: List<LocaleCurrencyDto>
) : BaseResponse