package com.sunman.binlist.data.model.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sunman.binlist.domain.model.Type

@Entity(tableName = "Card")
data class CardEntity(
    @PrimaryKey val bin: Int,
    @Embedded(prefix = "Number_") val number: NumberEntity?,
    val scheme: String?,
    val type: Type?,
    val brand: String?,
    val prepaid: Boolean?,
    val countryNumber: Int?,
    val bankName: String?
)
