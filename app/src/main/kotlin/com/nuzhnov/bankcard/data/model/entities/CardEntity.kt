package com.nuzhnov.bankcard.data.model.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nuzhnov.bankcard.domain.model.Type

@Entity(tableName = "Card")
data class CardEntity(
    @PrimaryKey val bin: String,
    @Embedded(prefix = "Number_") val number: NumberEntity?,
    val scheme: String?,
    val type: Type?,
    val brand: String?,
    val prepaid: Boolean?,
    val countryNumber: Int?,
    val bankName: String?
)
