package com.sunman.binlist.domain.model

data class Country(
    val number: Int,
    val shortcut: String,
    val name: String,
    val emojiIcon: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int
)
