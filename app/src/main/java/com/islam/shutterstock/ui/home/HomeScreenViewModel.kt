package com.islam.shutterstock.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.islam.shutterstock.data.network.dataSource.HomeDataSource
import com.islam.shutterstock.data.network.response.ImageDataResponse
import com.islam.shutterstock.data.repositories.SearchImageRepository
import com.islam.shutterstock.generalUtils.PAGE_SIZE
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeScreenViewModel @Inject constructor(private val repository: SearchImageRepository) :
    ViewModel() {

    fun searchResults(): Flowable<PagingData<ImageDataResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                HomeDataSource(repository)
            }
        ).flowable.cachedIn(viewModelScope)
    }

}