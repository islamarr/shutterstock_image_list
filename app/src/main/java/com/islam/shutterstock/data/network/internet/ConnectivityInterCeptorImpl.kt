package com.islam.shutterstock.data.network.internet

import android.content.Context
import com.islam.shutterstock.R
import com.islam.shutterstock.generalUtils.NoInternetException
import com.islam.shutterstock.generalUtils.Utils
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterCeptorImpl(var context: Context) : ConnectivityInterCeptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utils.isOnline(context)) throw NoInternetException(context.resources.getString(R.string.no_internet_connection))
        return chain.proceed(chain.request())
    }

}