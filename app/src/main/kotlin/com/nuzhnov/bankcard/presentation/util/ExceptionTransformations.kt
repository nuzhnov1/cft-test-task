package com.nuzhnov.bankcard.presentation.util

import android.content.Context
import com.nuzhnov.bankcard.R
import retrofit2.HttpException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.JsonDataException
import java.io.IOException

fun Throwable.toString(context: Context) = when (this) {
    is HttpException -> {
        when (code()) {
            400 -> context.getString(R.string.http400Error)
            401 -> context.getString(R.string.http401Error)
            403 -> context.getString(R.string.http403Error)
            429 -> context.getString(R.string.http429Error)
            500 -> context.getString(R.string.http500Error)
            502 -> context.getString(R.string.http502Error)
            503 -> context.getString(R.string.http503Error)
            504 -> context.getString(R.string.http504Error)
            else -> context.getString(R.string.httpOtherError, message())
        }
    }

    is JsonEncodingException, is JsonDataException -> context.getString(R.string.invalidResponseError)
    is IOException -> context.getString(R.string.networkError, localizedMessage ?: "none")
    else -> context.getString(R.string.otherError, localizedMessage ?: "none")
}
