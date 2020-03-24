package com.touktw.weverseshop.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.touktw.weverseshop.db.dao.ArtistDao
import com.touktw.weverseshop.db.dao.LocaleCurrencyDao
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.model.LocaleCurrencyDto

/**
 * Created by taekim on 2020-03-24
 */


@Database(
    entities = [LocaleCurrencyDto::class, ArtistDto::class], version = 1
)
@TypeConverters(RoomTypeConverter::class)
abstract class WeverseDatabase : RoomDatabase() {
    abstract fun localeCurrencyDao(): LocaleCurrencyDao

    abstract fun artistDao(): ArtistDao
}