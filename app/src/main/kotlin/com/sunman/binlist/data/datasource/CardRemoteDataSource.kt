package com.sunman.binlist.data.datasource

import com.sunman.binlist.data.api.ICardApi
import com.sunman.binlist.data.model.CardApiModel
import com.sunman.binlist.di.annotations.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardRemoteDataSource @Inject constructor(
    private val cardApi: ICardApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getCardByBin(bin: String): Result<CardApiModel?> = withContext(ioDispatcher) {
        cardApi.getCardByBin(bin)
    }
}
