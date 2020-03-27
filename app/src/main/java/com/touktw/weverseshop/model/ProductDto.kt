package com.touktw.weverseshop.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.touktw.weverseshop.base.db.BaseDto

/**
 * Created by taekim on 2020-03-24
 */

@Entity(tableName = "product")
data class ProductDto(
        @PrimaryKey
        val id: Int,
        val title: String,
        val description: String,
        val imageUrl: String,
        val isOnly: Boolean,
        val isReserve: Boolean,
        val isSoldOut: Boolean,
        val price: Float,
        val artistId: String,
        val category: String,
        val tag: String
) : BaseDto {
    @ColumnInfo
    var lastSelectTime: Long = 0
}