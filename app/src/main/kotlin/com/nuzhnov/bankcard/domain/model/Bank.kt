package com.nuzhnov.bankcard.domain.model

import com.nuzhnov.bankcard.domain.annotation.Unique

data class Bank(
    @Unique val name: String,
    val url: String?,
    val phone: String?,
    val city: String?
)
