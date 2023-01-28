package com.nuzhnov.bankcard.domain.model

import com.nuzhnov.bankcard.domain.annotation.Unique

data class Card(
    @Unique val bin: String,
    val number: Number?,
    val scheme: String?,
    val type: Type?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)
