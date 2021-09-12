package com.islam.shutterstock.generalUtils

import android.content.Context
import android.net.ConnectivityManager
import androidx.viewbinding.BuildConfig

object Utils {

    private const val DEV_URL = "https://api.shutterstock.com/"
    private const val URL = "https://api.shutterstock.com/"

    fun getUrl(): String {
        return if (BuildConfig.DEBUG)
            DEV_URL
        else
            URL
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}