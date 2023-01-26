package com.sunman.binlist.data.repository

import com.sunman.binlist.data.datasource.CardLocalDataSource
import com.sunman.binlist.data.datasource.CardRemoteDataSource
import com.sunman.binlist.data.mapper.toEntity
import com.sunman.binlist.data.mapper.toModel
import com.sunman.binlist.data.model.CardEntityModel
import com.sunman.binlist.domain.model.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cardLocalDataSource: CardLocalDataSource,
    private val cardRemoteDataSource: CardRemoteDataSource
) : IRepository {

    private var currentCard: Card? = null
    private val mutex = Mutex()

    override val savedCards: Flow<List<Card>> = cardLocalDataSource
        .getAllCards()
        .map { cardsList -> cardsList.map(CardEntityModel::toModel) }


    override suspend fun getCardByBin(bin: String): Result<Card?> = mutex.withLock {
        if ((currentCard == null) || (currentCard != null && currentCard?.bin != bin)) {
            val localResult = cardLocalDataSource.getCardByBin(bin)

            if (localResult != null) {
                currentCard = localResult.toModel()
                Result.success(currentCard)
            } else {
                val remoteResult = cardRemoteDataSource.getCardByBin(bin)

                remoteResult.map {
                    currentCard = it?.toModel(bin)
                    currentCard
                }
            }
        } else {
            Result.success(currentCard)
        }
    }

    override suspend fun saveCurrentCard() = mutex.withLock {
        if (currentCard != null) {
            val entity = currentCard!!.toEntity()
            val bin = entity.cardEntity.bin

            if (cardLocalDataSource.isCardExist(bin)) {
                cardLocalDataSource.updateCard(entity)
            } else {
                cardLocalDataSource.insertCard(entity)
            }
        }
    }

    override suspend fun removeAllCards() = cardLocalDataSource.removeAllCards()
}
