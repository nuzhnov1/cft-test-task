package com.nuzhnov.bankcard.di

import com.nuzhnov.bankcard.data.repository.IRepository
import com.nuzhnov.bankcard.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Suppress("unused")
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun provideRepository(repositoryImpl: RepositoryImpl): IRepository
}
