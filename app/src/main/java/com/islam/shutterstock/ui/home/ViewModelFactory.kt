package com.islam.shutterstock.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.shutterstock.data.repositories.SearchImageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class ViewModelFactory @Inject constructor(private var searchImageRepository: SearchImageRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(searchImageRepository) as T
    }

}
