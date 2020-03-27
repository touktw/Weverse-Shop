package com.touktw.weverseshop.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.touktw.weverseshop.base.db.BaseDao
import com.touktw.weverseshop.model.PreferenceDto

@Dao
interface PreferenceDao : BaseDao<PreferenceDto> {
    @Query("SELECT * FROM preference WHERE id='1'")
    fun getPreference(): LiveData<PreferenceDto?>

    @Query("SELECT * FROM preference WHERE id='1'")
    suspend fun getPreferenceSync(): PreferenceDto?
}