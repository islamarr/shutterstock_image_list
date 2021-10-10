package com.islam.shutterstock.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.islam.shutterstock.data.network.dataSource.HomeDataSource
import com.islam.shutterstock.data.repositories.SearchImageRepository
import com.islam.shutterstock.generalUtils.PAGE_SIZE
import com.islam.shutterstock.ui.intent.MainIntent
import com.islam.shutterstock.ui.viewstate.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: SearchImageRepository) :
    ViewModel() {

    val searchIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            searchIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.SearchResults -> fetchImages()
                }
            }
        }
    }

    private fun fetchImages() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                pagingData().collectLatest {
                    _state.value = MainState.ImageData(it)
                }
            } catch (e: Exception) {
                _state.value = MainState.Error(e.localizedMessage)
            }
        }
    }

    private fun pagingData() =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                HomeDataSource(repository)
            }
        ).flow.cachedIn(viewModelScope)

}