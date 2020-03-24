package com.touktw.weverseshop.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by taekim on 2020-03-24
 */

@Entity(tableName = "currency")
data class CurrencyDto(
        val symbol: String,
        val name: String,
        @PrimaryKey
        val code: String
)

@Entity(tableName = "locale")
data class LocaleDto(
        @PrimaryKey
        val locale: Locale
) {
    @Ignore
    var currency: CurrencyDto? = null
}