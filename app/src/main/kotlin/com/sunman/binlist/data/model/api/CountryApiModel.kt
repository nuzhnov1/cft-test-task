package com.sunman.binlist.data.model.api

import com.squareup.moshi.Json

data class CountryApiModel(
    @Json(name = "numeric") val number: Int,
    @Json(name = "alpha2") val shortcut: String,
    val name: String,
    @Json(name = "emoji") val emojiIcon: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int
)
