package com.touktw.weverseshop.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.touktw.weverseshop.base.db.BaseDao
import com.touktw.weverseshop.model.LocaleCurrencyDto

/**
 * Created by taekim on 2020-03-24
 */


@Dao
interface LocaleCurrencyDao : BaseDao<LocaleCurrencyDto> {
    @Query("SELECT * FROM localeCurrency")
    fun getAll(): LiveData<List<LocaleCurrencyDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(vararg t: LocaleCurrencyDto)
}