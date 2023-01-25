package com.sunman.binlist.presentation.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import com.sunman.binlist.R
import com.sunman.binlist.databinding.CardInfoBinding
import com.sunman.binlist.domain.model.*
import com.sunman.binlist.domain.model.Number

fun CardInfoBinding.setCard(card: Card?)= run {
    val context = root.context

    scheme.text = card?.scheme
    brand.text = card?.brand
    type.text = card?.type.toString(context)
    prepaid.text = card?.prepaid.toString(context)
    setNumber(card?.number, context)
    setCountry(card?.country, context)
    setBank(card?.bank, context)
}

private fun Type?.toString(context: Context) = when (this) {
    Type.CREDIT -> context.getString(R.string.debit)
    Type.DEBIT -> context.getString(R.string.credit)
    else -> null
}

private fun Boolean?.toString(context: Context) = when (this) {
    true -> context.getString(R.string.yes)
    false -> context.getString(R.string.no)
    else -> null
}

private fun CardInfoBinding.setNumber(number: Number?, context: Context) {
    length.text = number?.length.toString()
    luhn.text = number?.isUsingLuhn.toString(context)
}

private fun CardInfoBinding.setCountry(country: Country?, context: Context) {
    val countryIconAndNameString: String?
    val coordinatesString: String
    val numberString: String
    val shortcutString: String
    val currencyString: String

    if (country != null) {
        countryIconAndNameString = context.getString(
            R.string.countryIconAndName,
            country.emojiIcon,
            country.name
        )

        coordinatesString = context.getString(
            R.string.countryCoordinates,
            country.latitude.toString(),
            country.longitude.toString()
        )

        numberString = context.getString(R.string.countryNumber, country.number.toString())
        shortcutString = context.getString(R.string.countryShortcut, country.shortcut)
        currencyString = context.getString(R.string.countryCurrency, country.currency)
    } else {
        countryIconAndNameString = null

        coordinatesString = context.getString(
            R.string.countryCoordinates, "", ""
        )

        numberString = context.getString(R.string.countryNumber, "")
        shortcutString = context.getString(R.string.countryShortcut, "")
        currencyString = context.getString(R.string.countryCurrency, "")
    }

    countryIconAndName.text = countryIconAndNameString

    if (Build.VERSION.SDK_INT < 24) {
        countryCoordinates.text = Html.fromHtml(coordinatesString)
        countryNumber.text = Html.fromHtml(numberString)
        countryShortcut.text = Html.fromHtml(shortcutString)
        countryCurrency.text = Html.fromHtml(currencyString)
    } else {
        countryCoordinates.text = Html.fromHtml(
            coordinatesString,
            Html.FROM_HTML_MODE_COMPACT
        )

        countryNumber.text = Html.fromHtml(numberString, Html.FROM_HTML_MODE_COMPACT)
        countryShortcut.text = Html.fromHtml(shortcutString, Html.FROM_HTML_MODE_COMPACT)
        countryCurrency.text = Html.fromHtml(currencyString, Html.FROM_HTML_MODE_COMPACT)
    }

    country?.apply {
        countryCoordinates.setOnClickListener { onCoordinatesClick(latitude, longitude) }
    }
}

private fun CardInfoBinding.setBank(bank: Bank?, context: Context) {
    bankNameAndCity.text = bank?.run {
        context.getString(R.string.bankNameAndCity, bank.name, bank.city)
    }

    bankUrl.text = bank?.url
    bankPhone.text = bank?.phone
}

private fun CardInfoBinding.onCoordinatesClick(latitude: Int, longitude: Int) {
    val context = root.context
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("geo:$latitude,$longitude")
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
