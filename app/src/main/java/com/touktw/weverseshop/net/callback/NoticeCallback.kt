package com.touktw.weverseshop.net.callback

import com.touktw.weverseshop.base.net.BaseCallback
import com.touktw.weverseshop.net.response.NoticeResponse

/**
 * Created by taekim on 2020-03-24
 */


abstract class NoticeCallback : BaseCallback<NoticeResponse>(NoticeResponse::class.java)