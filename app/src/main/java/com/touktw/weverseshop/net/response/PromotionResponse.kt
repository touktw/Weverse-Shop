package com.touktw.weverseshop.net.response

import com.touktw.weverseshop.base.net.BaseResponse
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.model.PromotionDto

/**
 * Created by taekim on 2020-03-24
 */

data class PromotionResponse(
        val data: List<PromotionDto>
) : BaseResponse