package com.touktw.weverseshop.net

import com.touktw.weverseshop.base.net.BaseHttpClient
import okhttp3.Headers

/**
 * Created by taekim on 2020-03-24
 */

class WeverseShopHttpClient : BaseHttpClient() {
    override fun getHeaders(): Headers? {
        return null
    }

    override val baseUrl: String
        get() = "https://us-central1-weverse-shop.cloudfunctions.net/$SERVER_VERSION/"

    companion object {
        private const val SERVER_VERSION = "v1"
    }
}