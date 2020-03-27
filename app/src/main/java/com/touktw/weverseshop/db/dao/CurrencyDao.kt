package com.touktw.weverseshop.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.touktw.weverseshop.base.db.BaseDao
import com.touktw.weverseshop.model.CurrencyDto


@Dao
interface CurrencyDao : BaseDao<CurrencyDto> {
    @Query("SELECT * FROM currency")
    fun getAll(): LiveData<List<CurrencyDto>?>

    @Query("SELECT * FROM currency WHERE code=:code")
    fun get(code: String): LiveData<CurrencyDto?>
}