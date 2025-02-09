package com.sakthi.shyaway.feature_wear_list.presendation.wear

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear
import com.sakthi.shyaway.feature_wear_list.domain.repository.WearRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WearViewmodel @Inject constructor(
    private val repository: WearRepository
): ViewModel() {

    var sortOptions by mutableStateOf(listOf(SortOption()))

    init {
        getSortOptions()
    }

    fun getWear(urlKey: String, sortBy: String, sortDirection: String): Flow<PagingData<Wear>> {
        return repository.getAllWear(urlKey, sortBy, sortDirection).cachedIn(viewModelScope)
    }

    private fun getSortOptions() {
        viewModelScope.launch {
            repository.getWearSortOptions()
                .collect { result ->
                    val newList = result.getOrDefault(emptyList())

                    sortOptions = emptyList()
                    sortOptions = newList
                }
        }
    }

    fun onEvent(event: WearScreenEvent) {
        when(event) {
            WearScreenEvent.Latest -> TODO()
            WearScreenEvent.OnSearch -> TODO()
            WearScreenEvent.Trending -> TODO()
        }
    }

}