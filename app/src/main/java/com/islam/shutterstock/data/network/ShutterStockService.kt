package com.islam.shutterstock.data.network

import retrofit2.http.GET
import retrofit2.http.Headers

interface ShutterStockService {

    @GET("")
    suspend fun searchImages()

}

