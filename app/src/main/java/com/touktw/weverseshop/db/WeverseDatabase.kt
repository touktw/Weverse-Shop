package com.touktw.weverseshop.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.touktw.weverseshop.db.dao.*
import com.touktw.weverseshop.model.*

/**
 * Created by taekim on 2020-03-24
 */


@Database(entities = [LocaleDto::class, ArtistDto::class,
    CurrencyDto::class, ProductDto::class, PreferenceDto::class], version = 3)
@TypeConverters(RoomTypeConverter::class)
abstract class WeverseDatabase : RoomDatabase() {
    abstract fun localeDao(): LocaleDao

    abstract fun artistDao(): ArtistDao

    abstract fun currencyDao(): CurrencyDao

    abstract fun productDao(): ProductDao

    abstract fun preferenceDao(): PreferenceDao
}