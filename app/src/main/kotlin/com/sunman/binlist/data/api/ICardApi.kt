package com.sunman.binlist.data.api

import com.sunman.binlist.data.model.CardApiModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ICardApi {
    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getCardByBin(@Path("bin") bin: String): Result<CardApiModel?>
}
