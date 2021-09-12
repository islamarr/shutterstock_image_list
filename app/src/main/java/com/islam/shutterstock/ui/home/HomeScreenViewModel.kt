package com.islam.shutterstock.ui.home

import androidx.lifecycle.ViewModel
import com.islam.shutterstock.data.network.repositories.SearchImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: SearchImageRepository) :
    ViewModel() {

    suspend fun searchResults(query: String) {
        repository.searchImages(query, 1,1)
    }

}