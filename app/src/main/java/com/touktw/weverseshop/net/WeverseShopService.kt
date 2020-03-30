package com.touktw.weverseshop.net

import android.os.Build
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*

/**
 * Created by taekim on 2020-03-24
 */

object WeverseShopService {
    private val client = WeverseShopHttpClient()

    fun get(): WeverseShopApi {
        return Retrofit.Builder()
                .baseUrl(client.baseUrl)
                .client(client.client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeverseShopApi::class.java)
    }

    internal class LocaleDeserializer : JsonDeserializer<Locale> {
        override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
        ): Locale {
            return json?.asString?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Locale.forLanguageTag(it)
                } else {
                    val splits = it.split("-")
                    Locale(splits[0], splits[1])
                }
            } ?: Locale.getDefault()
        }

    }
}