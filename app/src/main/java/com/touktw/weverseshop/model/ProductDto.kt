package com.touktw.weverseshop.model

/**
 * Created by taekim on 2020-03-24
 */

data class ProductDto(
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
)