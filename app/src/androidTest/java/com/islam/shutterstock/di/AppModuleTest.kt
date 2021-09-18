package com.islam.shutterstock.di

import com.islam.shutterstock.data.repositories.FakeSearchImageRepoUITest
import com.islam.shutterstock.data.repositories.SearchImageRepository
import com.islam.shutterstock.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object AppModuleTest {

    @Singleton
    @Provides
        fun provideFakeSearchImageRepoUITest() =
        FakeSearchImageRepoUITest() as SearchImageRepository

}

















