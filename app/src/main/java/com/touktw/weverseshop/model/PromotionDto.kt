package com.touktw.weverseshop.model

import com.touktw.weverseshop.base.db.BaseDto

data class PromotionDto(
        val title: String,
        val description: String,
        val imageUrl: String,
        val artistId: Int
) : BaseDto