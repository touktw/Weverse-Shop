package com.touktw.weverseshop.base.net

import android.os.Build
import android.util.Log
import com.google.gson.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.*

/**
 * Created by taekim on 2020-03-24
 */


abstract class BaseCallback<T : BaseResponse>(private val clazz: Class<T>) : Callback<ApiResponse> {
    private val parser = JsonParser()
    private val gson =
        GsonBuilder()
            .registerTypeAdapter(Locale::class.java, LocaleDeserializer())
            .create()

    abstract fun onSuccess(response: T)
    abstract fun onFailed(code: Int, t: Throwable? = null)
    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
        Log.d("###", "onFailure ${t.message}")
        onFailed(Results.ERROR_UNKNOWN, t)
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

    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
        when {
            response.isSuccessful -> {
                var success = false
                var result: T? = null
                try {
                    val dataString = response.body()?.data
                    val data = parser.parse(dataString)
                    val json = JsonObject()
                    json.add("data", data)

                    result = gson.fromJson(json.toString(), clazz)
                    success = true
                } catch (e: Exception) {
                    Log.e(LOG, "Exception occurred e:${e.message}", e)
                }

                if (success && result != null) {
                    onSuccess(result)
                } else {
                    onFailed(Results.ERROR_UNKNOWN)
                }

            }
            else -> {
                onFailed(response.code())
            }
        }
    }

    companion object {
        private const val LOG = "BaseCallback"
    }
}