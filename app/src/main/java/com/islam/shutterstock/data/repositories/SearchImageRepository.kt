package com.islam.shutterstock.data.repositories

import com.islam.shutterstock.data.Resource
import com.islam.shutterstock.data.network.response.ImageResponse

interface SearchImageRepository {

    suspend fun searchImages(token: String, page: Int, pageSize: Int): Resource<ImageResponse>

}