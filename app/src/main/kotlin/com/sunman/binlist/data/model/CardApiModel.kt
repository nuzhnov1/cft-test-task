package com.sunman.binlist.data.model

import com.sunman.binlist.data.model.api.BankApiModel
import com.sunman.binlist.data.model.api.CountryApiModel
import com.sunman.binlist.data.model.api.NumberApiModel
import com.sunman.binlist.domain.model.Type

data class CardApiModel(
    val number: NumberApiModel?,
    val scheme: String?,
    val type: Type?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryApiModel?,
    val bank: BankApiModel?
)
