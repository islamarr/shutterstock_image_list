package com.islam.shutterstock.di

import android.content.Context
import com.islam.shutterstock.ui.base.MainActivity
import com.islam.shutterstock.ui.home.HomeScreenFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [NetworkModule::class, DataModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(fragment: HomeScreenFragment)

}


