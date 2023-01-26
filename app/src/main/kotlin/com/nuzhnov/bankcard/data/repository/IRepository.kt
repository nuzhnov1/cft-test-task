package com.nuzhnov.bankcard.data.repository

import com.nuzhnov.bankcard.domain.model.Card
import kotlinx.coroutines.flow.Flow

interface IRepository {
    val savedCards: Flow<List<Card>>

    suspend fun getCardByBin(bin: String): Result<Card?>
    suspend fun saveCurrentCard()
    suspend fun removeAllCards()
}
