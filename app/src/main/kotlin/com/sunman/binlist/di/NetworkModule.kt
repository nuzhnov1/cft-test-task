package com.sunman.binlist.di

import com.sunman.binlist.data.network.adapters.CardTypeAdapter
import com.sunman.binlist.configuration.SERVICE_URL
import com.squareup.moshi.Moshi
import com.sunman.binlist.data.api.ICardApi
import com.sunman.binlist.data.network.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Provides
    @Singleton
    fun provideCardApi(retrofit: Retrofit): ICardApi = retrofit.create(ICardApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(SERVICE_URL)
        .build()

    @Provides
    @Singleton
    fun provideMosh(): Moshi = Moshi.Builder()
        .add(CardTypeAdapter)
        .build()
}
