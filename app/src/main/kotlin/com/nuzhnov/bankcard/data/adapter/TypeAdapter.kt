package com.nuzhnov.bankcard.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.nuzhnov.bankcard.domain.model.Type

@Suppress("unused", "unused_parameter")
object TypeAdapter {
    @ToJson fun toJson(type: Type?): String {
        throw UnsupportedOperationException()
    }

    @FromJson fun fromJson(type: String): Type? = when (type) {
        "debit" -> Type.DEBIT
        "credit" -> Type.CREDIT
        else -> null
    }
}
