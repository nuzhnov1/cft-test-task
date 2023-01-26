package com.nuzhnov.bankcard.presentation.util

fun CharSequence.isNumber() = !Regex("\\d+").matches(this)
