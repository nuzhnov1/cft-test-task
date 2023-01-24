package com.sunman.binlist.data.datasource

import com.sunman.binlist.data.dao.IBankDao
import com.sunman.binlist.data.dao.ICountryDao
import com.sunman.binlist.data.dao.ICardDao
import com.sunman.binlist.data.model.CardEntityModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class CardLocalDataSource(
    private val cardDao: ICardDao,
    private val countryDao: ICountryDao,
    private val bankDao: IBankDao,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getCardByBin(bin: String): CardEntityModel? = withContext(ioDispatcher) {
        cardDao.getByBin(bin)
    }

    suspend fun isCardExist(bin: String) = getCardByBin(bin) != null

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
