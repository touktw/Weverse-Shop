package com.touktw.weverseshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.touktw.weverseshop.base.db.BaseDto
import java.util.*

@Entity(tableName = "preference")
data class PreferenceDto(
        val artistId: Int,
        val locale: Locale,
        val currencyCode: String,
        @PrimaryKey
        val id: Int = 1
) : BaseDto