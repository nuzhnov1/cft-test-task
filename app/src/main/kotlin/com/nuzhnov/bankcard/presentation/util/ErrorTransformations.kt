package com.nuzhnov.bankcard.presentation.util

import android.content.Context
import com.nuzhnov.bankcard.R
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toString(context: Context) = when (this) {
    is HttpException -> {
        when (code()) {
            400 -> R.string.http400Error.toString(context)
            401 -> R.string.http401Error.toString(context)
            403 -> R.string.http403Error.toString(context)
            429 -> R.string.http429Error.toString(context)
            500 -> R.string.http500Error.toString(context)
            502 -> R.string.http502Error.toString(context)
            503 -> R.string.http503Error.toString(context)
            504 -> R.string.http504Error.toString(context)
            else -> R.string.httpOtherError.toString(context, message())
        }
    }

    is JsonEncodingException, is JsonDataException ->
        R.string.invalidResponseError.toString(context)

    is IOException ->
        R.string.networkError.toString(context, localizedMessage)

    else ->
        R.string.otherError.toString(context, localizedMessage)
}
