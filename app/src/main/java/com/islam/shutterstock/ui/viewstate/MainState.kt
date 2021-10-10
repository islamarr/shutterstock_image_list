package com.islam.shutterstock.ui.viewstate

import androidx.paging.PagingData
import com.islam.shutterstock.data.network.response.ImageDataResponse


sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class ImageData(val imageDataResponse: PagingData<ImageDataResponse>) : MainState()
    data class Error(val error: String?) : MainState()

}