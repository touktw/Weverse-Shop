package com.touktw.weverseshop.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.touktw.weverseshop.base.db.BaseDao
import com.touktw.weverseshop.model.ArtistDto

/**
 * Created by taekim on 2020-03-24
 */

@Dao
interface ArtistDao : BaseDao<ArtistDto> {
    @Query("SELECT * FROM artist")
    fun getAll(): LiveData<List<ArtistDto>?>

    @Query("SELECT * FROM artist")
    fun getAllSync(): List<ArtistDto?>

    @Query("SELECT * FROM artist WHERE id=(:artistId)")
    fun getById(artistId: Int): LiveData<ArtistDto?>
}