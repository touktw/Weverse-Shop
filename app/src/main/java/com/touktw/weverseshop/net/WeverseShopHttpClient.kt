package com.touktw.weverseshop.net

import com.google.gson.Gson
import com.touktw.weverseshop.base.net.BaseHttpClient
import okhttp3.Headers
import okhttp3.MediaType

/**
 * Created by taekim on 2020-03-24
 */

class WeverseShopHttpClient : BaseHttpClient() {
    private val gson = Gson()
    private val json = MediaType.parse("application/json; charset=utf-8")
    override fun getHeaders(): Headers? {
        return null
    }

    override val baseUrl: String
        get() = "https://us-central1-weverse-shop.cloudfunctions.net/$SERVER_VERSION/"

    companion object {
        private const val SERVER_VERSION = "v1"
    }
}