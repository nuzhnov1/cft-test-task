package com.sunman.binlist.domain.model

data class Card(
    val bin: Int,
    val number: Number?,
    val scheme: String?,
    val type: Type?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)
