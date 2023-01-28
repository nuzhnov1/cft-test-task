package com.nuzhnov.bankcard.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nuzhnov.bankcard.data.dao.IBankDao
import com.nuzhnov.bankcard.data.dao.ICardDao
import com.nuzhnov.bankcard.data.dao.ICountryDao
import com.nuzhnov.bankcard.data.model.entities.BankEntity
import com.nuzhnov.bankcard.data.model.entities.CardEntity
import com.nuzhnov.bankcard.data.model.entities.CountryEntity

@Database(
    entities = [BankEntity::class, CountryEntity::class, CardEntity::class],
    version = 2,
    exportSchema = false
)
abstract class CardDatabase : RoomDatabase() {
    abstract fun bankDao(): IBankDao
    abstract fun countryDao(): ICountryDao
    abstract fun cardDao(): ICardDao
}
