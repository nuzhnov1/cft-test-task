package com.sunman.binlist.data.repository

import com.sunman.binlist.domain.model.Card
import kotlinx.coroutines.flow.Flow

interface IRepository {
    val savedCards: Flow<List<Card>>

    suspend fun getCardByBin(bin: Int): Result<Card?>
    suspend fun saveCurrentCard()
    suspend fun removeAllCards()
}
