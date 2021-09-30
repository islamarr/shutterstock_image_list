package com.islam.shutterstock.data.network.dataSource

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.islam.shutterstock.BuildConfig
import com.islam.shutterstock.data.network.response.ImageDataResponse
import com.islam.shutterstock.data.network.response.ImageResponse
import com.islam.shutterstock.data.repositories.SearchImageRepository
import com.islam.shutterstock.generalUtils.PAGE_SIZE
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeDataSource(private val repository: SearchImageRepository) :
    RxPagingSource<Int, ImageDataResponse>() {

    companion object {
        private const val START_INDEX = 1
        private const val TAG = "HomeDataSource"
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ImageDataResponse>> {
        val page = params.key ?: START_INDEX

        return repository.searchImages(BuildConfig.TOKEN, page, PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(
        response: ImageResponse,
        page: Int
    ): LoadResult<Int, ImageDataResponse> {
        val imageList: List<ImageDataResponse> = response.data
        return LoadResult.Page(
            data = imageList,
            prevKey = if (page <= START_INDEX) null else page - 1,
            nextKey = if (imageList.isEmpty()) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ImageDataResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}