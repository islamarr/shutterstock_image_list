package com.islam.shutterstock.data.repositories

import com.islam.shutterstock.data.Resource
import com.islam.shutterstock.data.network.response.ImageDataResponse
import com.islam.shutterstock.data.network.response.ImageResponse

class FakeSearchImageRepositoryTest(
    private val fakeImages: List<ImageDataResponse>,
    private var shouldReturnNetworkError: Boolean
) : SearchImageRepository {

    override suspend fun searchImages(
        token: String,
        page: Int,
        pageSize: Int
    ): Resource<ImageResponse> {

        return if (shouldReturnNetworkError) {
            Resource.Error("Error")
        } else {
            Resource.Success(
                createImageResponse()
            )
        }

    }

    private fun createImageResponse(): ImageResponse {
        return ImageResponse(
            data = fakeImages,
            page = 1,
            perPage = 1,
            searchId = "1",
            totalCount = fakeImages.size
        )
    }

}