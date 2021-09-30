package com.islam.shutterstock.data.repositories

import com.islam.shutterstock.data.network.ShutterStockService
import com.islam.shutterstock.data.network.response.ImageResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchImageRepositoryImpl @Inject constructor(private val api: ShutterStockService) :
    SearchImageRepository {

    override fun searchImages(
        token: String,
        page: Int,
        pageSize: Int
    ): Single<ImageResponse> {

        return api.searchImages(token, page, pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private const val TAG = "SearchImageRepoImpl"
    }

}