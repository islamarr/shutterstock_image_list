package com.islam.shutterstock.data.repositories

import com.islam.shutterstock.data.Resource
import com.islam.shutterstock.data.network.ShutterStockService
import com.islam.shutterstock.data.network.response.ImageResponse
import com.islam.shutterstock.generalUtils.*
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
                } ?: Resource.Error(UNKNOWN_ERROR)
            } else {
                Resource.Error(UNKNOWN_ERROR)
            }
        } catch (e: ApiException) {
            Utils.loge(TAG, e.toString())
            Resource.Error(UNKNOWN_ERROR)
        } catch (ne: NoInternetException) {
            Utils.loge(TAG, ne.toString())
            Resource.Error(INTERNET_ERROR)
        }
    }

    companion object {
        private const val TAG = "SearchImageRepoImpl"
    }

}