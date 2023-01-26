package com.nuzhnov.bankcard.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.nuzhnov.bankcard.data.model.entities.BankEntity
import com.nuzhnov.bankcard.data.model.entities.CardEntity
import com.nuzhnov.bankcard.data.model.entities.CountryEntity

data class CardEntityModel(
    @Embedded val cardEntity: CardEntity,
    @Relation(parentColumn = "countryNumber", entityColumn = "number")
    val countryEntity: CountryEntity?,
    @Relation(parentColumn = "bankName", entityColumn = "name")
    val bankEntity: BankEntity?
)
