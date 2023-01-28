package com.nuzhnov.bankcard.data.datasource

import com.nuzhnov.bankcard.data.dao.IBankDao
import com.nuzhnov.bankcard.data.dao.ICountryDao
import com.nuzhnov.bankcard.data.dao.ICardDao
import com.nuzhnov.bankcard.data.model.CardEntityModel
import com.nuzhnov.bankcard.di.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardLocalDataSource @Inject constructor(
    private val cardDao: ICardDao,
    private val countryDao: ICountryDao,
    private val bankDao: IBankDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getCardByBin(bin: String): CardEntityModel? = withContext(ioDispatcher) {
        cardDao.getByBin(bin)
    }

    suspend fun isCardExist(bin: String): Boolean = getCardByBin(bin) != null

    fun getAllCards(): Flow<List<CardEntityModel>> = cardDao
        .getAll()
        .distinctUntilChanged()
        .flowOn(ioDispatcher)

    suspend fun insertCard(card: CardEntityModel) = withContext(ioDispatcher) {
        if (card.countryEntity != null) {
            countryDao.insertAll(card.countryEntity)
        }

        if (card.bankEntity != null) {
            bankDao.insertAll(card.bankEntity)
        }

        cardDao.insertAll(card.cardEntity)
    }

    suspend fun updateCard(card: CardEntityModel) = withContext(ioDispatcher) {
        if (card.countryEntity != null) {
            countryDao.updateAll(card.countryEntity)
        }

        if (card.bankEntity != null) {
            bankDao.updateAll(card.bankEntity)
        }

        cardDao.updateAll(card.cardEntity)
    }

    // For the future
    @Suppress("unused")
    suspend fun deleteCard(card: CardEntityModel) = withContext(ioDispatcher) {
        val countryNumber = card.countryEntity?.number
        val bankName = card.bankEntity?.name

        cardDao.deleteAll(card.cardEntity)

        if (countryNumber != null && countryDao.getUsagesCount(countryNumber) == 0) {
            countryDao.deleteAll(card.countryEntity)
        }

        if (bankName != null && bankDao.getUsagesCount(bankName) == 0) {
            bankDao.deleteAll(card.bankEntity)
        }
    }

    suspend fun removeAllCards() = withContext(ioDispatcher) {
        cardDao.clear()
        countryDao.clear()
        bankDao.clear()
    }
}
