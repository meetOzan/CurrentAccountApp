package com.mertozan.currentaccountapp.di

import com.mertozan.currentaccountapp.data.repository.CaRepositoryImpl
import com.mertozan.currentaccountapp.domain.CaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCaRepository(caRepositoryImpl: CaRepositoryImpl): CaRepository

}