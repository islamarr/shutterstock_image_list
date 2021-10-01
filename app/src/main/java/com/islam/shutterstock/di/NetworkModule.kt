package com.islam.shutterstock.di

import android.content.Context
import com.islam.shutterstock.BuildConfig
import com.islam.shutterstock.data.network.ShutterStockService
import com.islam.shutterstock.data.network.interceptors.ConnectivityInterceptor
import com.islam.shutterstock.data.network.interceptors.ConnectivityInterceptorImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideConnectivityInterceptor(context: Context): ConnectivityInterceptor {
        return ConnectivityInterceptorImpl(context)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideAPI(
        connectivityInterceptor: ConnectivityInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): ShutterStockService {

        val okkHttpclient = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(connectivityInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(ShutterStockService::class.java)

    }

}

















