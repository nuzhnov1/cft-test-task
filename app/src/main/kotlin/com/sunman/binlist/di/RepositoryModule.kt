package com.sunman.binlist.di

import com.sunman.binlist.data.repository.IRepository
import com.sunman.binlist.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun provideRepository(repositoryImpl: RepositoryImpl): IRepository

}
