package com.sunman.binlist.data.mapper

import com.sunman.binlist.data.model.CardApiModel
import com.sunman.binlist.data.model.api.NumberApiModel
import com.sunman.binlist.data.model.api.CountryApiModel
import com.sunman.binlist.data.model.api.BankApiModel
import com.sunman.binlist.data.model.CardEntityModel
import com.sunman.binlist.data.model.entities.NumberEntity
import com.sunman.binlist.data.model.entities.CountryEntity
import com.sunman.binlist.data.model.entities.BankEntity
import com.sunman.binlist.data.model.entities.CardEntity
import com.sunman.binlist.domain.model.Card
import com.sunman.binlist.domain.model.Number
import com.sunman.binlist.domain.model.Country
import com.sunman.binlist.domain.model.Bank

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


fun CardApiModel.toModel(bin: Int) = Card(
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

fun CountryApiModel.toModel() = Country(
    number = number,
    shortcut = shortcut,
    name = name,
    emojiIcon = emojiIcon,
    currency = currency,
    latitude = latitude,
    longitude = longitude
)

fun BankApiModel.toModel() = Bank(
    name = name,
    url = url,
    phone = phone,
    city = city
)


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
