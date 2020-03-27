package com.touktw.weverseshop.db

import android.os.Build
import androidx.room.TypeConverter
import java.util.*

/**
 * Created by taekim on 2020-03-24
 */


class RoomTypeConverter {
    @TypeConverter
    fun fromStringToLocale(value: String): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Locale.forLanguageTag(value) ?: Locale.getDefault()
        } else {
            val split = value.split("-")
            Locale(split[0], split[1])
        }
    }

    @TypeConverter
    fun fromLocaleToString(value: Locale): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            value.toLanguageTag()
        } else {
            "${value.language}-${value.country}"
        }
    }
}