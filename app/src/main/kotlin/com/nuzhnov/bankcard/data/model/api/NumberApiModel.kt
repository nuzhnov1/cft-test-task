package com.nuzhnov.bankcard.data.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NumberApiModel(
    val length: Int,
    @Json(name = "luhn") val isUsingLuhn: Boolean
)
