package com.islam.shutterstock.generalUtils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.islam.shutterstock.BuildConfig

object Utils {

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun loge(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.e(tag, message)
    }

}