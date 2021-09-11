package com.islam.shutterstock.generalUtils

import android.content.Context
import android.net.ConnectivityManager
import androidx.viewbinding.BuildConfig

object Utils {

    const val DEVURL = ""
    const val URL = ""

    fun getUrl(): String {
        return if (BuildConfig.DEBUG)
            DEVURL
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