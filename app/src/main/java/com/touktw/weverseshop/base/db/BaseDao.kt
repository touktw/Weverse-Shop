package com.touktw.weverseshop.base.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by taekim on 2020-03-24
 */


interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg t: T)
}