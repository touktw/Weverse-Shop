package com.touktw.weverseshop.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.touktw.weverseshop.base.db.BaseDao
import com.touktw.weverseshop.model.ProductDto

/**
 * Created by taekim on 2020-03-24
 */

@Dao
interface ProductDao : BaseDao<ProductDto> {
    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<ProductDto>?>

    @Query("SELECT * FROM product")
    fun getAllSync(): List<ProductDto>?

    @Query("SELECT * FROM product WHERE artistId=(:artistId)")
    fun getByArtistId(artistId: Int): LiveData<List<ProductDto>?>

    @Query("SELECT * FROM product WHERE artistId=(:artistId)")
    suspend fun getByArtistIdSync(artistId: Int): List<ProductDto>?
}