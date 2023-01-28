package com.nuzhnov.bankcard.presentation.util

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.annotation.StringRes
import com.nuzhnov.bankcard.R
import com.nuzhnov.bankcard.domain.model.*


fun <T> T?.toString(context: Context) = when (this) {
    null -> context.getString(R.string.unknown)
    else -> toString()
}

fun <T> @receiver:StringRes Int.toString(
    context: Context,
    vararg args: T?
) = context.getString(
    this,
    *args.map { it.toString(context) }.toTypedArray()
)

fun @receiver:StringRes Int.toString(context: Context) = context.getString(this)

fun <T> @receiver:StringRes Int.toSpanned(
    context: Context,
    vararg args: T?
) = context.getString(
    this,
    *args.map { it.toString(context) }.toTypedArray()
).toSpanned()

fun @receiver:StringRes Int.toSpanned(context: Context) =
    context.getString(this).toSpanned()

fun String.toSpanned(): Spanned {
    return if (Build.VERSION.SDK_INT < 24) {
        Html.fromHtml(this)
    } else {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    }
}

fun Type?.toSpanned(context: Context) = when (this) {
    Type.CREDIT -> R.string.credit.toSpanned(context)
    Type.DEBIT -> R.string.debit.toSpanned(context)
    else -> R.string.unknownType.toSpanned(context)
}

fun Boolean?.toSpanned(context: Context) = when (this) {
    true -> R.string.yes.toSpanned(context)
    false -> R.string.no.toSpanned(context)
    else -> R.string.unknownAnswer.toSpanned(context)
}
