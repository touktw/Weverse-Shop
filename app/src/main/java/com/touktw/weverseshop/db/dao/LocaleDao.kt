package com.touktw.weverseshop.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.touktw.weverseshop.base.db.BaseDao
import com.touktw.weverseshop.model.LocaleDto

/**
 * Created by taekim on 2020-03-24
 */


@Dao
interface LocaleDao : BaseDao<LocaleDto> {
    @Query("SELECT * FROM locale")
    fun getAll(): LiveData<List<LocaleDto>?>

    @Query("SELECT * FROM locale")
    suspend fun getAllSync(): List<LocaleDto>?
}