package com.sunman.binlist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
abstract class AppDatabase : RoomDatabase() {
    abstract fun bankDao(): IBankDao
    abstract fun countryDao(): ICountryDao
    abstract fun cardDao(): ICardDao

    companion object {
        private const val DATABASE_NAME = "binlist"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(applicationContext: Context) : AppDatabase =
            synchronized(AppDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration().build()
                }

                return INSTANCE!!
            }
    }
}