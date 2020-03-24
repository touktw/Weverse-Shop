package com.touktw.weverseshop.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.touktw.weverseshop.db.dao.ArtistDao
import com.touktw.weverseshop.db.dao.CurrencyDao
import com.touktw.weverseshop.db.dao.LocaleDao
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.model.CurrencyDto
import com.touktw.weverseshop.model.LocaleDto

/**
 * Created by taekim on 2020-03-24
 */


@Database(
        entities = [LocaleDto::class, ArtistDto::class, CurrencyDto::class], version = 1
)
@TypeConverters(RoomTypeConverter::class)
abstract class WeverseDatabase : RoomDatabase() {
    abstract fun localeDao(): LocaleDao

    abstract fun artistDao(): ArtistDao

    abstract fun currencyDao(): CurrencyDao
}