package com.nuzhnov.bankcard.data.mapper

import com.nuzhnov.bankcard.data.model.CardApiModel
import com.nuzhnov.bankcard.data.model.CardEntityModel
import com.nuzhnov.bankcard.data.model.api.BankApiModel
import com.nuzhnov.bankcard.data.model.api.CountryApiModel
import com.nuzhnov.bankcard.data.model.api.NumberApiModel
import com.nuzhnov.bankcard.data.model.entities.BankEntity
import com.nuzhnov.bankcard.data.model.entities.CardEntity
import com.nuzhnov.bankcard.data.model.entities.CountryEntity
import com.nuzhnov.bankcard.data.model.entities.NumberEntity
import com.nuzhnov.bankcard.domain.model.Bank
import com.nuzhnov.bankcard.domain.model.Card
import com.nuzhnov.bankcard.domain.model.Country
import com.nuzhnov.bankcard.domain.model.Number

fun Card.toEntity() = CardEntityModel(
    cardEntity = CardEntity(
        bin = bin,
        number = number?.toEntity(),
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        countryNumber = country?.number,
        bankName = bank?.name
    ),
    countryEntity = country?.toEntity(),
    bankEntity = bank?.toEntity()
)

fun Number.toEntity() = NumberEntity(
    length = length,
    isUsingLuhn = isUsingLuhn
)

fun Country.toEntity() = CountryEntity(
    number = number,
    shortcut = shortcut,
    name = name,
    emojiIcon = emojiIcon,
    currency = currency,
    latitude = latitude,
    longitude = longitude
)

fun Bank.toEntity() = BankEntity(
    name = name,
    url = url,
    phone = phone,
    city = city
)


fun CardApiModel.toModel(bin: String) = Card(
    bin = bin,
    number = number?.toModel(),
    scheme = scheme,
    type = type,
    brand = brand,
    prepaid = prepaid,
    country = country?.toModel(),
    bank = bank?.toModel()
)

fun NumberApiModel.toModel() = Number(
    length = length,
    isUsingLuhn = isUsingLuhn
)

fun CountryApiModel.toModel(): Country? {
    return if (number == null) {
        null
    } else {
        Country(
            number = number,
            shortcut = shortcut,
            name = name,
            emojiIcon = emojiIcon,
            currency = currency,
            latitude = latitude,
            longitude = longitude
        )
    }
}

fun BankApiModel.toModel(): Bank? {
    return if (name == null) {
        null
    } else {
        Bank(
            name = name,
            url = url,
            phone = phone,
            city = city
        )
    }
}


fun CardEntityModel.toModel() = Card(
    bin = cardEntity.bin,
    number = cardEntity.number?.toModel(),
    scheme = cardEntity.scheme,
    type = cardEntity.type,
    brand = cardEntity.brand,
    prepaid = cardEntity.prepaid,
    country = countryEntity?.toModel(),
    bank = bankEntity?.toModel()
)

fun NumberEntity.toModel() = Number(
    length = length,
    isUsingLuhn = isUsingLuhn
)

fun CountryEntity.toModel() = Country(
    number = number,
    shortcut = shortcut,
    name = name,
    emojiIcon = emojiIcon,
    currency = currency,
    latitude = latitude,
    longitude = longitude
)

fun BankEntity.toModel() = Bank(
    name = name,
    url = url,
    phone = phone,
    city = city
)
