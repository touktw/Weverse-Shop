package com.touktw.weverseshop.base.db

import androidx.room.Insert

/**
 * Created by taekim on 2020-03-24
 */


interface BaseDao<T> {
    @Insert
    fun insert(vararg t: T)
}