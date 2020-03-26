package com.touktw.weverseshop.net.callback

import com.touktw.weverseshop.base.net.BaseCallback
import com.touktw.weverseshop.net.response.ProductResponse

/**
 * Created by taekim on 2020-03-24
 */


abstract class ProductCallback : BaseCallback<ProductResponse>(ProductResponse::class.java)