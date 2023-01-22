package com.sunman.binlist.data.model.api

import com.squareup.moshi.Json

data class NumberApiModel(
    val length: Int,
    @Json(name = "luhn") val isUsingLuhn: Boolean
)
