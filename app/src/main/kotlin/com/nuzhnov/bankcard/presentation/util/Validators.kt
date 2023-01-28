package com.nuzhnov.bankcard.presentation.util

fun CharSequence.isBankCardNumber() = Regex("\\d+").matches(this) && length <= 16
