package com.touktw.weverseshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.touktw.weverseshop.base.db.BaseDto

@Entity(tableName = "currency")
data class CurrencyDto(
        val symbol: String,
        val name: String,
        @PrimaryKey
        val code: String
) : BaseDto