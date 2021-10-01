package com.islam.shutterstock

import android.app.Application
import com.islam.shutterstock.di.AppComponent
import com.islam.shutterstock.di.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ShutterStockApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}