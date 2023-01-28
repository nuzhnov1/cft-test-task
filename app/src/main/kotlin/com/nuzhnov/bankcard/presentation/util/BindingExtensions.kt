package com.nuzhnov.bankcard.presentation.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.nuzhnov.bankcard.R
import com.nuzhnov.bankcard.databinding.CardInfoBinding
import com.nuzhnov.bankcard.domain.model.Bank
import com.nuzhnov.bankcard.domain.model.Card
import com.nuzhnov.bankcard.domain.model.Country
import com.nuzhnov.bankcard.domain.model.Number

fun CardInfoBinding.setCard(card: Card?)= run {
    val context = root.context

    bin.text = card?.bin.toString(context)
    scheme.text = card?.scheme.toString(context)
    brand.text = card?.brand.toString(context)
    type.text = card?.type.toSpanned(context)
    prepaid.text = card?.prepaid.toSpanned(context)
    setNumber(card?.number, context)
    setCountry(card?.country, context)
    setBank(card?.bank, context)
}

private fun CardInfoBinding.setNumber(number: Number?, context: Context) {
    length.text = number?.length.toString(context)
    luhn.text = number?.isUsingLuhn.toSpanned(context)
}

private fun CardInfoBinding.setCountry(country: Country?, context: Context) {
    countryIconAndName.text = R.string.countryIconAndName.toString(
        context,
        country?.emojiIcon,
        country?.name
    )
    countryCoordinates.text = R.string.countryCoordinates.toSpanned(
        context,
        country?.latitude,
        country?.longitude
    )
    countryNumber.text = R.string.countryNumber.toSpanned(context, country?.number)
    countryShortcut.text = R.string.countryShortcut.toSpanned(context, country?.shortcut)
    countryCurrency.text = R.string.countryCurrency.toSpanned(context, country?.currency)

    country?.apply {
        if (latitude != null && longitude != null) {
            countryCoordinates.setOnClickListener { onCoordinatesClick(latitude, longitude) }
        }
    }
}

private fun CardInfoBinding.setBank(bank: Bank?, context: Context) {
    bankName.text = R.string.bankName.toSpanned(context, bank?.name)
    bankCity.text = R.string.bankCity.toSpanned(context, bank?.city)
    bankUrl.text = bank?.url.toString(context)
    bankPhone.text = bank?.phone.toString(context)
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
