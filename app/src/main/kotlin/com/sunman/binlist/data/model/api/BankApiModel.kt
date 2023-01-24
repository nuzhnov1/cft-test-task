package com.sunman.binlist.data.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BankApiModel(
    val name: String,
    val url: String,
    val phone: String,
    val city: String
)
