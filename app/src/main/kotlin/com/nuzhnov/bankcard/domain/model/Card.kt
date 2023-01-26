package com.nuzhnov.bankcard.domain.model

data class Card(
    val bin: String,
    val number: Number?,
    val scheme: String?,
    val type: Type?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)
