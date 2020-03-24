package com.touktw.weverseshop.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by taekim on 2020-03-24
 */


@Entity(tableName = "localeCurrency")
data class LocaleCurrencyDto(
    @PrimaryKey
    val locale: Locale,
    @Embedded
    val currency: Currency
) {
    data class Currency(
        val symbol: String,
        val name: String,
        val code: String
    )
}