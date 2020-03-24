package com.touktw.weverseshop.db

import android.content.Context
import androidx.room.Room

/**
 * Created by taekim on 2020-03-24
 */


object DatabaseManager {
    fun get(applicationContext: Context): WeverseDatabase {
        return Room
            .databaseBuilder(applicationContext, WeverseDatabase::class.java, "weverse-shop")
            .addMigrations()
            .build()
    }
}