package com.nuzhnov.bankcard.di

import android.content.Context
import androidx.room.Room
import com.nuzhnov.bankcard.configuration.DATABASE_NAME
import com.nuzhnov.bankcard.data.dao.IBankDao
import com.nuzhnov.bankcard.data.dao.ICardDao
import com.nuzhnov.bankcard.data.dao.ICountryDao
import com.nuzhnov.bankcard.data.database.CardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBankDao(cardDatabase: CardDatabase): IBankDao = cardDatabase.bankDao()

    @Provides
    @Singleton
    fun provideCountryDao(cardDatabase: CardDatabase): ICountryDao = cardDatabase.countryDao()

    @Provides
    @Singleton
    fun provideCardDao(cardDatabase: CardDatabase): ICardDao = cardDatabase.cardDao()

    @Provides
    @Singleton
    fun provideCardDatabase(@ApplicationContext context: Context): CardDatabase =
        Room.databaseBuilder(
            context,
            CardDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
}
