package com.touktw.weverseshop.net.callback

import com.touktw.weverseshop.base.net.BaseCallback
import com.touktw.weverseshop.net.response.ArtistResponse
import com.touktw.weverseshop.net.response.PromotionResponse

/**
 * Created by taekim on 2020-03-24
 */


abstract class PromotionCallback : BaseCallback<PromotionResponse>(PromotionResponse::class.java)