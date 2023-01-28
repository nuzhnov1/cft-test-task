package com.nuzhnov.bankcard.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class CountryEntity(
    @PrimaryKey val number: Int,
    val shortcut: String?,
    val name: String?,
    val emojiIcon: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?
)
