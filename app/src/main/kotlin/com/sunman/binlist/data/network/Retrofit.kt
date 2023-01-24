package com.sunman.binlist.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val SERVICE_URL = "https://binlist.net/"

val retrofit: Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(ResultCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(SERVICE_URL)
    .build()
