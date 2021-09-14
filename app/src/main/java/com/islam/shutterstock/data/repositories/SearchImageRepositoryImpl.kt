package com.islam.shutterstock.data.repositories

import com.islam.shutterstock.data.Resource
import com.islam.shutterstock.data.network.ShutterStockService
import com.islam.shutterstock.data.network.response.ImageResponse
import com.islam.shutterstock.generalUtils.ApiException
import com.islam.shutterstock.generalUtils.NoInternetException
import com.islam.shutterstock.generalUtils.Utils
import javax.inject.Inject

class SearchImageRepositoryImpl @Inject constructor(private val api: ShutterStockService) :
    SearchImageRepository {

    override suspend fun searchImages(
        token: String,
        page: Int,
        pageSize: Int
    ): Resource<ImageResponse> {

        return try {
            val response = api.searchImages(token, page, pageSize)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("Something went wrong, try again!")
            } else {
                Resource.Error("Something went wrong, try again!")
            }
        } catch (e: ApiException) {
            Utils.loge(TAG, e.toString())
            Resource.Error("Something went wrong, try again!")
        } catch (ne: NoInternetException) {
            Utils.loge(TAG, ne.toString())
            Resource.Error("Make sure you have an active Internet connection!")
        }
    }

    companion object {
        private const val TAG = "SearchImageRepoImpl"
    }

}