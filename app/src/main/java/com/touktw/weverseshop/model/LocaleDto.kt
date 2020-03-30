package com.touktw.weverseshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.touktw.weverseshop.base.db.BaseDto
import java.util.*

/**
 * Created by taekim on 2020-03-24
 */

@Entity(tableName = "locale")
data class LocaleDto(
        @PrimaryKey
        val id: Int,
        val language: String,
        val country: String?
) : BaseDto {
    fun getLocale(): Locale {
        val country = country ?: return Locale(language)

        return Locale(language, country)
    }

}