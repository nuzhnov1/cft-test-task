package com.sunman.binlist.data.datasource

import com.sunman.binlist.data.api.ICardApi
import com.sunman.binlist.data.model.CardApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CardRemoteDataSource(
    private val cardApi: ICardApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getCardByBin(bin: String): Result<CardApiModel?> = withContext(ioDispatcher) {
        cardApi.getCardByBin(bin)
    }
}
