package com.nuzhnov.bankcard.domain.model

import com.nuzhnov.bankcard.domain.annotation.Unique

data class Country(
    @Unique val number: Int,
    val shortcut: String?,
    val name: String?,
    val emojiIcon: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?
)
