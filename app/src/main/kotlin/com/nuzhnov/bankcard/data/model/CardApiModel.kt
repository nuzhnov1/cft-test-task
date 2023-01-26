package com.nuzhnov.bankcard.data.model

import com.squareup.moshi.JsonClass
import com.nuzhnov.bankcard.data.model.api.BankApiModel
import com.nuzhnov.bankcard.data.model.api.CountryApiModel
import com.nuzhnov.bankcard.data.model.api.NumberApiModel
import com.nuzhnov.bankcard.domain.model.Type

@JsonClass(generateAdapter = true)
data class CardApiModel(
    val number: NumberApiModel?,
    val scheme: String?,
    val type: Type?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryApiModel?,
    val bank: BankApiModel?
)
