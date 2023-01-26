package com.sunman.binlist.di

import android.content.Context
import androidx.room.Room
import com.sunman.binlist.configuration.DATABASE_NAME
import com.sunman.binlist.data.dao.IBankDao
import com.sunman.binlist.data.dao.ICardDao
import com.sunman.binlist.data.dao.ICountryDao
import com.sunman.binlist.data.database.CardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

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
