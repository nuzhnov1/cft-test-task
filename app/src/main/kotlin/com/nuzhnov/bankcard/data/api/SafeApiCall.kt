package com.nuzhnov.bankcard.data.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): Result<T> = withContext(dispatcher) {
    try {
        Result.success(apiCall())
    } catch (t: Throwable) {
        Result.failure(t)
    }
}
