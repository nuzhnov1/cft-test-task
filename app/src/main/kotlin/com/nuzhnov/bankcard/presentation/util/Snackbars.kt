package com.nuzhnov.bankcard.presentation.util

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun showIndefiniteSnackbar(context: Context, parent: View, message: String) = Snackbar
    .make(context, parent, message, Snackbar.LENGTH_INDEFINITE)
    .show()

fun showIndefiniteSnackbar(context: Context, parent: View, @StringRes message: Int) = Snackbar
    .make(context, parent, message.toString(context), Snackbar.LENGTH_INDEFINITE)
    .show()
