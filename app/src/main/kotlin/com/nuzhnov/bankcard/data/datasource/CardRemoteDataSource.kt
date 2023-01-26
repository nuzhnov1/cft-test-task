package com.nuzhnov.bankcard.data.datasource

import com.nuzhnov.bankcard.data.api.ICardApi
import com.nuzhnov.bankcard.data.model.CardApiModel
import com.nuzhnov.bankcard.data.api.safeApiCall
import com.nuzhnov.bankcard.di.annotations.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CardRemoteDataSource @Inject constructor(
    private val cardApi: ICardApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getCardByBin(bin: String): Result<CardApiModel?> = safeApiCall(ioDispatcher) {
        cardApi.getCardByBin(bin)
    }
}
