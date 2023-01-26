package com.nuzhnov.bankcard.data.repository

import com.nuzhnov.bankcard.data.datasource.CardLocalDataSource
import com.nuzhnov.bankcard.data.datasource.CardRemoteDataSource
import com.nuzhnov.bankcard.data.mapper.toEntity
import com.nuzhnov.bankcard.data.mapper.toModel
import com.nuzhnov.bankcard.data.model.CardEntityModel
import com.nuzhnov.bankcard.domain.model.Card
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
        // If a new card has been requested
        if ((currentCard == null) || (currentCard != null && currentCard?.bin != bin)) {
            // Looking bank card among the previously saved ones
            val localResult = cardLocalDataSource.getCardByBin(bin)

            if (localResult != null) {
                currentCard = localResult.toModel()
                Result.success(currentCard)
            } else {
                // If bank card was not founded among the previously saved ones
                // then making request to the service
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
