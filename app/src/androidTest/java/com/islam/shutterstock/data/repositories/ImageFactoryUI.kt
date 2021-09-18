package com.islam.shutterstock.data.repositories

import com.islam.shutterstock.data.network.response.Assets
import com.islam.shutterstock.data.network.response.ImageDataResponse
import com.islam.shutterstock.data.network.response.Preview
import java.util.concurrent.atomic.AtomicInteger

class ImageFactoryUI {

    private val counter = AtomicInteger(0)

    fun createImageItem(): ImageDataResponse {
        val id = counter.incrementAndGet()
        return ImageDataResponse(
            assets = Assets(
                Preview(
                    url = "https_$id"
                )
            ),
            description = "description_$id",
            id = "$id"
        )
    }
}