package com.islam.shutterstock.data.network

import com.islam.shutterstock.data.network.response.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ShutterStockService {

    @GET("v2/images/search")
    suspend fun searchImages(
        @Header("Authorization") token: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int,
    ): Response<ImageResponse>

}

