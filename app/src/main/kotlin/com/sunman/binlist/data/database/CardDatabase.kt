package com.sunman.binlist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sunman.binlist.data.dao.IBankDao
import com.sunman.binlist.data.dao.ICardDao
import com.sunman.binlist.data.dao.ICountryDao
import com.sunman.binlist.data.model.entities.BankEntity
import com.sunman.binlist.data.model.entities.CardEntity
import com.sunman.binlist.data.model.entities.CountryEntity

@Database(
    entities = [BankEntity::class, CountryEntity::class, CardEntity::class],
    version = 1
)
abstract class CardDatabase : RoomDatabase() {
    abstract fun bankDao(): IBankDao
    abstract fun countryDao(): ICountryDao
    abstract fun cardDao(): ICardDao
}
