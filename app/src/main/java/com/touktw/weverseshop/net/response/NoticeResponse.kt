package com.touktw.weverseshop.net.response

import com.touktw.weverseshop.base.net.BaseResponse
import com.touktw.weverseshop.model.NoticeDto

/**
 * Created by taekim on 2020-03-24
 */

data class NoticeResponse(val data: List<NoticeDto>) : BaseResponse