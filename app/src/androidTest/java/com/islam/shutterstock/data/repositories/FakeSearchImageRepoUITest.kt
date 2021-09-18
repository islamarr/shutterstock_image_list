package com.islam.shutterstock.data.repositories

import com.islam.shutterstock.data.Resource
import com.islam.shutterstock.data.network.response.ImageDataResponse
import com.islam.shutterstock.data.network.response.ImageResponse

class FakeSearchImageRepoUITest : SearchImageRepository {

    private var shouldReturnNetworkError = false
    private val imageFactory = ImageFactoryUI()
    private val fakeImages = mutableListOf<ImageDataResponse>()


    private fun fakeImages() {
        repeat(25) {
            fakeImages.add(imageFactory.createImageItem())
        }
    }


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
        fakeImages()
        return ImageResponse(
            data = fakeImages,
            page = 1,
            perPage = 1,
            searchId = "1",
            totalCount = fakeImages.size
        )
    }

}