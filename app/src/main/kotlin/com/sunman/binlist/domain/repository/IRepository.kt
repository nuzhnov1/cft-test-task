package com.sunman.binlist.domain.repository

import com.sunman.binlist.domain.model.Card
import kotlinx.coroutines.flow.Flow

interface IRepository {
    val savedCards: Flow<List<Card>>

    suspend fun getCardByBin(bin: Int): Card?
    suspend fun saveCurrentCard()
}
