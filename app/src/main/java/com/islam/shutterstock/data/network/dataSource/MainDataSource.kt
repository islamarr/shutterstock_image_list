package com.islam.shutterstock.data.network.dataSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.islam.shutterstock.data.Resource
import com.islam.shutterstock.data.network.repositories.SearchImageRepository
import com.islam.shutterstock.data.network.response.ImageDataResponse
import com.islam.shutterstock.generalUtils.PAGE_SIZE

class MainDataSource(private val query: String, private val repository: SearchImageRepository) :
    PagingSource<Int, ImageDataResponse>() {

    companion object {
        private const val START_INDEX = 1
        private const val TAG = "MainDataSource"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDataResponse> {

        return try {
            val page = params.key ?: START_INDEX
            val response =
                (repository.searchImages(query, page, PAGE_SIZE) as Resource.Success).data
            val imageList = response.data

            LoadResult.Page(
                data = imageList,
                prevKey = if (page <= START_INDEX) null else page - 1,
                nextKey = if (imageList.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            Log.e(TAG, exception.message.toString())
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageDataResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}