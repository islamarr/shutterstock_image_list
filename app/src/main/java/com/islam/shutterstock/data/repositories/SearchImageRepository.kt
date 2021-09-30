package com.islam.shutterstock.data.repositories

import com.islam.shutterstock.data.Resource
import com.islam.shutterstock.data.network.response.ImageResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface SearchImageRepository {

    fun searchImages(token: String, page: Int, pageSize: Int): Single<ImageResponse>

}