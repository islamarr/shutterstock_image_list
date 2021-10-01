package com.islam.shutterstock.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.islam.shutterstock.R
import com.islam.shutterstock.ShutterStockApp
import com.islam.shutterstock.di.AppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        appComponent = (application as ShutterStockApp).appComponent
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}