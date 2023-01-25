package com.sunman.binlist.presentation.util

fun CharSequence.isNumber() = !Regex("\\d+").matches(this)
