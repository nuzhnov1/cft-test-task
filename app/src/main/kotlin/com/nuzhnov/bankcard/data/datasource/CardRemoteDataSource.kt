package com.nuzhnov.bankcard.data.datasource

import com.nuzhnov.bankcard.data.api.ICardApi
import com.nuzhnov.bankcard.data.api.safeApiCall
import com.nuzhnov.bankcard.data.model.CardApiModel
import com.nuzhnov.bankcard.di.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.HttpException
import javax.inject.Inject

class CardRemoteDataSource @Inject constructor(
    private val cardApi: ICardApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getCardByBin(bin: String): Result<CardApiModel?> {
        val result = safeApiCall(ioDispatcher) { cardApi.getCardByBin(bin) }

        return result.fold(
            onSuccess = { Result.success(it) },
            onFailure = {
                if (it is HttpException && it.code() == 404) {
                    Result.success(null)
                } else {
                    Result.failure(it)
                }
            }
        )
    }
}
