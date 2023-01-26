package com.sunman.binlist.data.network.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.sunman.binlist.domain.model.Type

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
