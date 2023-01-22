package com.sunman.binlist.data.repository

import com.sunman.binlist.data.datasource.CardLocalDataSource
import com.sunman.binlist.data.datasource.CardRemoteDataSource
import com.sunman.binlist.data.mapper.toEntity
import com.sunman.binlist.data.mapper.toModel
import com.sunman.binlist.data.model.CardEntityModel
import com.sunman.binlist.domain.model.Card
import com.sunman.binlist.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RepositoryImpl(
    private val cardLocalDataSource: CardLocalDataSource,
    private val cardRemoteDataSource: CardRemoteDataSource
) : IRepository {

    private var currentCard: Card? = null
    private val mutex = Mutex()

    override val savedCards: Flow<List<Card>> = cardLocalDataSource
        .getAllCards()
        .map { cardsList -> cardsList.map(CardEntityModel::toModel) }


    override suspend fun getCardByBin(bin: Int): Card? = mutex.withLock {
        if ((currentCard == null) || (currentCard != null && currentCard?.bin != bin)) {
            currentCard = cardLocalDataSource.getCardByBin(bin)
                ?.toModel()
                ?: cardRemoteDataSource.getCardByBin(bin)?.toModel(bin)
        }

        currentCard
    }

    override suspend fun saveCurrentCard() = mutex.withLock {
        currentCard?.toEntity()?.apply {
            if (getCardByBin(cardEntity.bin) != null) {
                cardLocalDataSource.updateCard(this)
            } else {
                cardLocalDataSource.insertCard(this)
            }
        }

        Unit
    }
}
