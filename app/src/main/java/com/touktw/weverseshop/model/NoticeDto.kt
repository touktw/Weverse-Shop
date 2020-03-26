package com.touktw.weverseshop.model

import com.touktw.weverseshop.base.db.BaseDto

data class NoticeDto(
        val notice: String,
        val createAt: Long
) : BaseDto