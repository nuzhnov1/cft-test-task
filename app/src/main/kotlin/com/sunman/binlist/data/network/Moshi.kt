package com.sunman.binlist.data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.sunman.binlist.domain.model.Type

val moshi: Moshi = Moshi.Builder()
    .add(CardTypeAdapter)
    .build()

object CardTypeAdapter {
    @ToJson fun toJson(type: Type) = when (type) {
        Type.DEBIT -> "debit"
        Type.CREDIT -> "credit"
    }

    @FromJson fun fromJson(type: String) = when (type) {
        "debit" -> Type.DEBIT
        else -> Type.CREDIT
    }
}
