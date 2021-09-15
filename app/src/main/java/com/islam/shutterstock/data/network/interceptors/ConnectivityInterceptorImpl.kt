package com.islam.shutterstock.data.network.interceptors

import android.content.Context
import com.islam.shutterstock.R
import com.islam.shutterstock.generalUtils.NoInternetException
import com.islam.shutterstock.generalUtils.Utils
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptorImpl @Inject constructor(var context: Context) : ConnectivityInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utils.isOnline(context)) throw NoInternetException(context.resources.getString(R.string.no_internet_connection))
        return chain.proceed(chain.request())
    }

}