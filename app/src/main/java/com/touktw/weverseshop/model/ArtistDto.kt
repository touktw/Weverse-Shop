package com.touktw.weverseshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by taekim on 2020-03-24
 */

@Entity(tableName = "artist")
data class ArtistDto(
    val name: String,
    @PrimaryKey
    val id: Int,
    val group: String,
    val groupId: Int,
    val logoUrl: String
)