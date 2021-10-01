package com.islam.shutterstock.di

import com.islam.shutterstock.data.network.ShutterStockService
import com.islam.shutterstock.data.repositories.SearchImageRepository
import com.islam.shutterstock.data.repositories.SearchImageRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object DataModule {

    @Singleton
    @Provides
    fun provideSearchRepository(api: ShutterStockService) =
        SearchImageRepositoryImpl(api) as SearchImageRepository

}