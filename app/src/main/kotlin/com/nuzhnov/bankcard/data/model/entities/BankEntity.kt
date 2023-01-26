package com.nuzhnov.bankcard.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bank")
data class BankEntity(
    @PrimaryKey val name: String,
    val url: String,
    val phone: String,
    val city: String
)
